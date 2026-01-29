package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record MovieReq(
        @NotBlank
        String title,

        String synopsis,

        @JsonAlias("release_year")
        Integer releaseYear) {
}
