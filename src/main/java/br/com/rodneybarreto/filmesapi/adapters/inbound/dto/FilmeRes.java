package br.com.rodneybarreto.filmesapi.adapters.inbound.dto;

import br.com.rodneybarreto.filmesapi.application.core.domain.entity.Filme;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FilmeRes(
        Long id,

        String titulo,

        String sinopse,

        @JsonProperty("ano_lancamento")
        Integer anoLancamento) {

    public FilmeRes(Filme filme) {
        this(filme.getId(), filme.getTitulo(), filme.getSinopse(), filme.getAnoLancamento());
    }

}
