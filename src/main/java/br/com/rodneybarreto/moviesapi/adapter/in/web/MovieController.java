package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieResponse;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.in.DeleteMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.in.ReadMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.in.UpdateMovieUseCase;
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
@RequestMapping(MovieController.RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

    protected static final String RESOURCE = "/v1/movies";

    private final CreateMovieUseCase createMovieUseCase;
    private final ReadMovieUseCase readMovieUseCase;
    private final UpdateMovieUseCase updateMovieUseCase;
    private final DeleteMovieUseCase deleteMovieUseCase;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody MovieRequest movieRequest, UriComponentsBuilder uriBuilder) {
        Movie movie = MovieRequest.toDomain(movieRequest);
        Movie createdMovie = createMovieUseCase.create(movie);
        URI uri = uriBuilder.path(RESOURCE + "/{id}").buildAndExpand(createdMovie.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieResponse> findById(@PathVariable long id) {
        Movie movie = readMovieUseCase.findById(id);
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
        PageResponse<Movie> page = readMovieUseCase.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
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
        updateMovieUseCase.update(id, movie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteMovieUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
