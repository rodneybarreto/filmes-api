package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.UpdateMovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class UpdateMovieUseCase implements UpdateMovieUseCasePort {

    private final MoviePersistence moviePersistence;

    public UpdateMovieUseCase(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public void update(Long id, Movie movie) {
        moviePersistence.update(id, movie);
    }

}
