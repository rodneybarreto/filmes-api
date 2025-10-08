package br.com.rodneybarreto.moviesapi.application.ports.outbound.rest;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;

public interface FindRestClientPort {

    Movie findByTitle(String title);

    Movie findById(String id);

}
