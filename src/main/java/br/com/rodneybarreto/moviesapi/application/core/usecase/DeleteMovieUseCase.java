package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.application.port.in.DeleteMovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class DeleteMovieUseCase implements DeleteMovieUseCasePort {

    private final MoviePersistence moviePersistence;

    public DeleteMovieUseCase(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public void delete(Long id) {
        moviePersistence.deleteById(id);
    }

}
