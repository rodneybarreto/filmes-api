package br.uece.eescomdevops.filmesapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record FilmeReq (
        @NotBlank
        String titulo,

        String sinopse,

        @JsonAlias("ano_lancamento")
        Integer anoLancamento
) {
}
