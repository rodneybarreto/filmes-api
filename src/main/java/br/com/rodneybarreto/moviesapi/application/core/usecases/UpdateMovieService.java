package br.com.rodneybarreto.moviesapi.application.core.usecases;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.UpdateMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.MovieRepositoryPort;

public class UpdateMovieService implements UpdateMovieServicePort {

    private final MovieRepositoryPort movieRepositoryPort;

    public UpdateMovieService(MovieRepositoryPort movieRepositoryPort) {
        this.movieRepositoryPort = movieRepositoryPort;
    }

    @Override
    public void update(Long id, Movie movie) {
        movieRepositoryPort.update(id, movie);
    }

}
