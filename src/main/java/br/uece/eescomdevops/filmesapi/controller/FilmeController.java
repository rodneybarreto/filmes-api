package br.uece.eescomdevops.filmesapi.controller;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeDto;
import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
import br.uece.eescomdevops.filmesapi.domain.dto.FilmeRes;
import br.uece.eescomdevops.filmesapi.domain.entity.Filme;
import br.uece.eescomdevops.filmesapi.repository.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(FilmeController.RESOURCE)
public class FilmeController {

    protected static final String RESOURCE = "/v1/filmes";

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody FilmeReq filmeReq, UriComponentsBuilder uriComponentsBuilder) {
        Filme filme = filmeRepository.save(new Filme(filmeReq));
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(filme.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FilmeRes>> findAll(@PageableDefault(sort = {"titulo"}) Pageable pageable) {
        Page<Filme> filmesPage = filmeRepository.findAll(pageable);
        return ResponseEntity.ok(filmesPage.map(FilmeRes::new));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeDto> findById(@PathVariable Long id) {
        Optional<Filme> optional = filmeRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new FilmeDto(optional.get()));
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

}
