package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateMovieUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class CreateMovieUseCase implements CreateMovieUseCasePort {

    private final MoviePersistence moviePersistence;

    public CreateMovieUseCase(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public Movie create(Movie movie) {
        return moviePersistence.create(movie);
    }

}
