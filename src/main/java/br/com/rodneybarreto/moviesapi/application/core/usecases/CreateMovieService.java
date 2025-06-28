package br.com.rodneybarreto.moviesapi.application.core.usecases;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.CreateMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.MovieRepositoryPort;

public class CreateMovieService implements CreateMovieServicePort {

    private final MovieRepositoryPort movieRepositoryPort;

    public CreateMovieService(MovieRepositoryPort movieRepositoryPort) {
        this.movieRepositoryPort = movieRepositoryPort;
    }

    @Override
    public Long create(Movie movie) {
        Movie movieSaved = movieRepositoryPort.save(movie);
        return movieSaved.getId();
    }

}
