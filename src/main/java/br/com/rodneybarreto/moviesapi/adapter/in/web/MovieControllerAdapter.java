package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieResponse;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateMovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.in.DeleteMovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.in.ReadMovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.in.UpdateMovieUseCasePort;
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
@RequestMapping(MovieControllerAdapter.RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieControllerAdapter {

    protected static final String RESOURCE = "/v1/movies";

    private final CreateMovieUseCasePort createMovieUseCasePort;
    private final ReadMovieUseCasePort readMovieUseCasePort;
    private final UpdateMovieUseCasePort updateMovieUseCasePort;
    private final DeleteMovieUseCasePort deleteMovieUseCasePort;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody MovieRequest movieRequest, UriComponentsBuilder uriBuilder) {
        Movie movie = MovieRequest.toDomain(movieRequest);
        Movie createdMovie = createMovieUseCasePort.create(movie);
        URI uri = uriBuilder.path(RESOURCE + "/{id}").buildAndExpand(createdMovie.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieResponse> findById(@PathVariable long id) {
        Movie movie = readMovieUseCasePort.findById(id);
        return ResponseEntity.ok(new MovieResponse(movie));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<MovieResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String searchTerm
    ) {
        PageResponse<Movie> page = readMovieUseCasePort.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
        return ResponseEntity.ok(
                new PageResponse<>(
                        MovieResponse.toList(page.getContent()),
                        page.getPageNumber(),
                        page.getPageSize(),
                        page.getTotalPages(),
                        page.getTotalElements()
                )
        );
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = MovieRequest.toDomain(movieRequest);
        updateMovieUseCasePort.update(id, movie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteMovieUseCasePort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
