package br.uece.eescomdevops.filmesapi.controller;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
import br.uece.eescomdevops.filmesapi.domain.dto.FilmeRes;
import br.uece.eescomdevops.filmesapi.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(FilmeController.RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilmeController {

    protected static final String RESOURCE = "/v1/filmes";

    private final FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody FilmeReq filmeReq, UriComponentsBuilder uriComponentsBuilder) {
        Long filmeId = filmeService.create(filmeReq);
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(filmeId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FilmeRes>> findAll(@PageableDefault(sort = {"titulo"}) Pageable pageable) {
        Page<FilmeRes> filmeResPage = filmeService.findAll(pageable);
        return ResponseEntity.ok(filmeResPage);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeRes> findById(@PathVariable Long id) {
        FilmeRes filmeRes = filmeService.findById(id);
        return ResponseEntity.ok(filmeRes);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeReq> update(@PathVariable Long id, @Valid @RequestBody FilmeReq filmeReq) {
        filmeService.update(id, filmeReq);
        return ResponseEntity.ok(filmeReq);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
