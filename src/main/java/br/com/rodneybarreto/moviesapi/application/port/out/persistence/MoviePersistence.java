package br.com.rodneybarreto.moviesapi.application.port.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageRes;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface MoviePersistence {

    Movie create(Movie movie);

    Movie findById(Long id);

    PageRes<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

    void update(Long id, Movie movie);

    void deleteById(Long id);

}
