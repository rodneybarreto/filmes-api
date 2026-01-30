package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.in.ReadMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;

public class ReadMovieService implements ReadMovieUseCase {

    private final MoviePersistence moviePersistence;

    public ReadMovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    @Override
    public Movie findById(Long id) {
        return moviePersistence.findById(id);
    }

    @Override
    public PageResponse<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        return moviePersistence.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
    }

}
