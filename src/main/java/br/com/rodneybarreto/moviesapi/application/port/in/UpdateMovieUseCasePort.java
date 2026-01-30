package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface UpdateMovieUseCasePort {

    void update(Long id, Movie movie);

}

