package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface CreateMovieUseCasePort {

    Movie create(Movie movie);

}
