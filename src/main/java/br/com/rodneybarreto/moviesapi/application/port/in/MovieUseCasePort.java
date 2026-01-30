package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface MovieUseCasePort {

    Movie createMovie(Movie movie);

    Movie findMovieById(Long id);

    PageResponse<Movie> findAllMovies(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

    void updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);

}
