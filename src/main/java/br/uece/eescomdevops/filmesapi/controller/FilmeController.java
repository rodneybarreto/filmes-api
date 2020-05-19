package br.uece.eescomdevops.filmesapi.controller;

import br.uece.eescomdevops.filmesapi.controller.dto.FilmeDto;
import br.uece.eescomdevops.filmesapi.model.Filme;
import br.uece.eescomdevops.filmesapi.repository.FilmeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(FilmeController.RESOURCE)
public class FilmeController {

    protected static final String RESOURCE = "/v1/filmes";

    private FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmeDto>> getAll() {
        List<Filme> filmes = filmeRepository.findAll();
        if (isEmpty(filmes)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmes.stream().map(FilmeDto::new).collect(toList()));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeDto> getById(@PathVariable Long id) {
        Optional<Filme> optional = filmeRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new FilmeDto(optional.get()));
    }

    @Transactional
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody FilmeDto filmeDto, UriComponentsBuilder uriComponentsBuilder) {
        Filme filme = filmeRepository.save(new Filme(filmeDto));
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(filme.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            filmeRepository.delete(filme.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody FilmeDto filmeDto) {
        Optional<Filme> optional = filmeRepository.findById(Long.parseLong(filmeDto.getId()));
        if (optional.isPresent()) {
            Filme filme = optional.get();
            filme.setTitulo(filmeDto.getTitulo());
            filme.setSinopse(filmeDto.getSinopse());
            filme.setAnoLancamento(filmeDto.getAnoLancamento());
            filme.setProdutores(filmeDto.getProdutores());
            filme.setProtagonistas(filmeDto.getProtagonistas());
            filmeRepository.save(filme);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
