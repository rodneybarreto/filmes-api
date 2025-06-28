package br.com.rodneybarreto.moviesapi.application.ports.outbound.repository;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface MovieRepositoryPort {

    Movie save(Movie movie);

    Movie findById(Long id);

    PageRes<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

    void update(Long id, Movie movie);

    void deleteById(Long id);

}
