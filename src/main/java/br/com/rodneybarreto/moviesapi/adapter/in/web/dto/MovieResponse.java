package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public record MovieResponse(
        long id,

        String title,

        String synopsis,

        @JsonProperty("release_year")
        int releaseYear
) {

    public MovieResponse(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getSynopsis(), movie.getReleaseYear());
    }

    public static List<MovieResponse> toList(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return Collections.emptyList();
        }
        return movies.stream().map(MovieResponse::new).toList();
    }

}
