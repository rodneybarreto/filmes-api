package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MovieRequest(
        @NotBlank(message = "The title is required")
        String title,

        String synopsis,

        @JsonAlias("release_year")
        Integer releaseYear
) {

    public static Movie toDomain(MovieRequest movieRequest) {
        return new Movie(movieRequest.title(), movieRequest.synopsis(), movieRequest.releaseYear());
    }

}
