package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface CreateMovieUseCase {

    Long create(Movie movie);

}
