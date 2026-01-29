package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.application.port.in.DeleteMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class DeleteMovieService implements DeleteMovieUseCase {

    private final MoviePersistence moviePersistence;

    public DeleteMovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public void delete(Long id) {
        moviePersistence.deleteById(id);
    }

}
