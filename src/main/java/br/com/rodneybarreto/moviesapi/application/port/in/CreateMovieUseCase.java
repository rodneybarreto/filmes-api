package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface CreateMovieUseCase {

    Movie create(Movie movie);

}
