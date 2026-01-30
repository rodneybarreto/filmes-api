package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.MovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class MovieUseCase implements MovieUseCasePort {

    private final MoviePersistence moviePersistence;

    public MovieUseCase(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return moviePersistence.create(movie);
    }

    @Override
    public Movie findMovieById(Long id) {
        return moviePersistence.findById(id);
    }

    @Override
    public PageResponse<Movie> findAllMovies(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        return moviePersistence.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
    }

    @Override
    public void updateMovie(Long id, Movie movie) {
        moviePersistence.update(id, movie);
    }

    @Override
    public void deleteMovie(Long id) {
        moviePersistence.deleteById(id);
    }

}
