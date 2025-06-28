package br.com.rodneybarreto.moviesapi.application.core.usecases;

import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.DeleteMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.MovieRepositoryPort;

public class DeleteMovieService implements DeleteMovieServicePort {

    private final MovieRepositoryPort movieRepositoryPort;

    public DeleteMovieService(MovieRepositoryPort movieRepositoryPort) {
        this.movieRepositoryPort = movieRepositoryPort;
    }

    @Override
    public void delete(Long id) {
        movieRepositoryPort.deleteById(id);
    }

}
