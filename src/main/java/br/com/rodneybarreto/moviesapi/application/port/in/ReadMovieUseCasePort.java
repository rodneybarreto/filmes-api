package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface ReadMovieUseCasePort {

    Movie findById(Long id);

    PageResponse<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

}
