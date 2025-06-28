package br.com.rodneybarreto.moviesapi.application.core.usecases;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.ReadMovieServicePort;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.MovieRepositoryPort;

public class ReadMovieService implements ReadMovieServicePort {

    private final MovieRepositoryPort movieRepositoryPort;

    public ReadMovieService(MovieRepositoryPort movieRepositoryPort) {
        this.movieRepositoryPort = movieRepositoryPort;
    }

    @Override
    public Movie findById(Long id) {
        return movieRepositoryPort.findById(id);
    }

    @Override
    public PageRes<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        return movieRepositoryPort.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
    }

}
