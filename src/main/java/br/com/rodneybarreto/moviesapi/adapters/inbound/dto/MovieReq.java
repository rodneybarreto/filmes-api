package br.com.rodneybarreto.moviesapi.adapters.inbound.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record MovieReq(
        @NotBlank
        String titulo,

        String sinopse,

        @JsonAlias("ano_lancamento")
        Integer anoLancamento) {
}
