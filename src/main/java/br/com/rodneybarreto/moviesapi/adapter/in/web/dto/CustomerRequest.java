package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CustomerRequest(
        @NotBlank(message = "The name is required")
        String name,

        @NotBlank(message = "The email is required")
        String email,

        @Size(max = 255)
        @JsonAlias("pix_key")
        String pixKey
) { }
