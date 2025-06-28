package br.com.rodneybarreto.moviesapi.application.ports.inbound.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface UpdateMovieServicePort {

    void update(Long id, Movie movie);

}

