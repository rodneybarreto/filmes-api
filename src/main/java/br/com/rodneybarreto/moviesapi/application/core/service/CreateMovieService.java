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
    public Movie create(Movie movie) {
        return moviePersistence.create(movie);
    }

}
