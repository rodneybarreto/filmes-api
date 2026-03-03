package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieResponse;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.MovieUseCasePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(MovieControllerAdapter.MOVIE_RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieControllerAdapter {

    public static final String MOVIE_RESOURCE = "/v1/movies";

    private final MovieUseCasePort movieUseCasePort;

    public MovieControllerAdapter(MovieUseCasePort movieUseCasePort) {
        this.movieUseCasePort = movieUseCasePort;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody MovieRequest movieRequest, UriComponentsBuilder uriBuilder) {
        Movie movie = MovieRequest.toDomain(movieRequest);
        Movie createdMovie = movieUseCasePort.createMovie(movie);
        URI uri = uriBuilder.path(MOVIE_RESOURCE + "/{id}").buildAndExpand(createdMovie.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieResponse> findOne(@PathVariable long id) {
        Movie movie = movieUseCasePort.findMovieById(id);
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
        PageResponse<Movie> page = movieUseCasePort.findAllMovies(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
        List<MovieResponse> list = MovieResponse.toList(page.getContent());
        return ResponseEntity.ok(
                new PageResponse<>(
                        list,
                        page.getPageNumber(),
                        page.getPageSize(),
                        page.getTotalPages(),
                        page.getTotalElements()
                )
        );
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = MovieRequest.toDomain(movieRequest);
        movieUseCasePort.updateMovie(id, movie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        movieUseCasePort.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
