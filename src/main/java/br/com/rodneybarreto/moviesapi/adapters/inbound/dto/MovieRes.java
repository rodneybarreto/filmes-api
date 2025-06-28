package br.com.rodneybarreto.moviesapi.adapters.inbound.dto;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieRes(
        Long id,

        String titulo,

        String sinopse,

        @JsonProperty("ano_lancamento")
        Integer anoLancamento) {

    public MovieRes(Movie movie) {
        this(movie.getId(), movie.getTitulo(), movie.getSinopse(), movie.getAnoLancamento());
    }

}
