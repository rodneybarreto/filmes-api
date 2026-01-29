package br.com.rodneybarreto.moviesapi.application.port.out.web;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface FindRestClientPort {

    Movie findByTitle(String title);

    Movie findById(String id);

}
