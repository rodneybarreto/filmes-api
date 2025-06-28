package br.com.rodneybarreto.moviesapi.application.ports.inbound.service;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface ReadMovieServicePort {

    Movie findById(Long id);

    PageRes<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

}
