package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.UpdateMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class UpdateMovieService implements UpdateMovieUseCase {

    private final MoviePersistence moviePersistence;

    public UpdateMovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public void update(Long id, Movie movie) {
        moviePersistence.update(id, movie);
    }

}
