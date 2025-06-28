package br.com.rodneybarreto.filmesapi.adapters.inbound.rest;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeReq;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeRes;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.filmesapi.adapters.mapper.FilmeMapper;
import br.com.rodneybarreto.filmesapi.application.core.domain.Filme;
import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.CreateFilmeServicePort;
import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.DeleteFilmeServicePort;
import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.ReadFilmeServicePort;
import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.UpdateFilmeServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(FilmeController.RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilmeController {

    protected static final String RESOURCE = "/v1/filmes";

    private final CreateFilmeServicePort createFilmeServicePort;
    private final ReadFilmeServicePort readFilmeServicePort;
    private final UpdateFilmeServicePort updateFilmeServicePort;
    private final DeleteFilmeServicePort deleteFilmeServicePort;
    private final FilmeMapper mapper;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody FilmeReq filmeReq, UriComponentsBuilder uriComponentsBuilder) {
        Filme filme = mapper.toFilme(filmeReq);
        Long filmeId = createFilmeServicePort.create(filme);
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(filmeId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeRes> findById(@PathVariable Long id) {
        Filme filme = readFilmeServicePort.findById(id);
        FilmeRes filmeRes = mapper.toFilmeRes(filme);
        return ResponseEntity.ok(filmeRes);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageRes<FilmeRes>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "ASC") String sortOrder,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        PageRes<Filme> page = readFilmeServicePort.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
        return ResponseEntity.ok(
                new PageRes<>(
                        mapper.toFilmeResList(page.getContent()),
                        page.getPageNumber(),
                        page.getPageSize(),
                        page.getTotalPages(),
                        page.getTotalElements()
                )
        );
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody FilmeReq filmeReq) {
        Filme filme = mapper.toFilme(filmeReq);
        updateFilmeServicePort.update(id, filme);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteFilmeServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
