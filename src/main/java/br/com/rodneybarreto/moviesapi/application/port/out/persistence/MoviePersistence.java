package br.com.rodneybarreto.moviesapi.application.port.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface MoviePersistence {

    Movie create(Movie movie);

    Movie findById(long id);

    PageResponse<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

    void update(long id, Movie movie);

    void deleteById(long id);

}
