package br.com.rodneybarreto.moviesapi.adapters.inbound.rest;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieReq;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieRes;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.moviesapi.adapters.mapper.MovieMapper;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.CreateMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.DeleteMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.ReadMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.UpdateMovieServicePort;
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
@RequestMapping(MovieRestAdapter.RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieRestAdapter {

    protected static final String RESOURCE = "/v1/movies";

    private final CreateMovieServicePort createMovieServicePort;
    private final ReadMovieServicePort readMovieServicePort;
    private final UpdateMovieServicePort updateMovieServicePort;
    private final DeleteMovieServicePort deleteMovieServicePort;
    private final MovieMapper mapper;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody MovieReq movieReq, UriComponentsBuilder uriComponentsBuilder) {
        Movie movie = mapper.toDomain(movieReq);
        Long filmeId = createMovieServicePort.create(movie);
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(filmeId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieRes> findById(@PathVariable Long id) {
        Movie movie = readMovieServicePort.findById(id);
        MovieRes movieRes = mapper.toResponse(movie);
        return ResponseEntity.ok(movieRes);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageRes<MovieRes>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "ASC") String sortOrder,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        PageRes<Movie> page = readMovieServicePort.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
        return ResponseEntity.ok(
                new PageRes<>(
                        mapper.toResponse(page.getContent()),
                        page.getPageNumber(),
                        page.getPageSize(),
                        page.getTotalPages(),
                        page.getTotalElements()
                )
        );
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody MovieReq movieReq) {
        Movie movie = mapper.toDomain(movieReq);
        updateMovieServicePort.update(id, movie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteMovieServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
