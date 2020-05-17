package br.uece.eescomdevops.filmesapi.controller;

import br.uece.eescomdevops.filmesapi.controller.dto.AvaliacaoDto;
import br.uece.eescomdevops.filmesapi.model.Avaliacao;
import br.uece.eescomdevops.filmesapi.repository.AvaliacaoRepository;
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
@RequestMapping(AvaliacaoController.RESOURCE)
public class AvaliacaoController {

    protected static final String RESOURCE = "/v1/avaliacoes";

    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AvaliacaoDto>> getAll() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        if (isEmpty(avaliacoes)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes.stream().map(AvaliacaoDto::new).collect(toList()));
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AvaliacaoDto> getById(@PathVariable Long id) {
        Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AvaliacaoDto(optional.get()));
    }

    @Transactional
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody AvaliacaoDto avaliacaoDto, UriComponentsBuilder uriComponentsBuilder) {
        Avaliacao avaliacao = avaliacaoRepository.save(new Avaliacao(avaliacaoDto));
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(avaliacao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao.isPresent()) {
            avaliacaoRepository.delete(avaliacao.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody AvaliacaoDto avaliacaoDto) {
        Optional<Avaliacao> optional = avaliacaoRepository.findById(Long.parseLong(avaliacaoDto.getId()));
        if (optional.isPresent()) {
            Avaliacao avaliacao = optional.get();
            avaliacao.setPessoaNome(avaliacaoDto.getPessoaNome());
            avaliacao.setComentario(avaliacaoDto.getComentario());
            avaliacao.setNota(Integer.parseInt(avaliacaoDto.getNota()));
            avaliacaoRepository.save(avaliacao);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
