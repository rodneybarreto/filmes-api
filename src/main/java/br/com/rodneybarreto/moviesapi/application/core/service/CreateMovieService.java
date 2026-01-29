package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class CreateMovieService implements CreateMovieUseCase {

    private final MoviePersistence moviePersistence;

    public CreateMovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public Long create(Movie movie) {
        Movie movieSaved = moviePersistence.create(movie);
        return movieSaved.getId();
    }

}
