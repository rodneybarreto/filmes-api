package br.com.rodneybarreto.moviesapi.adapters.inbound.dto;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieRes(
        Long id,

        String title,

        String synopsis,

        @JsonProperty("release_year")
        Integer releaseYear) {

    public MovieRes(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getSynopsis(), movie.getReleaseYear());
    }

}
