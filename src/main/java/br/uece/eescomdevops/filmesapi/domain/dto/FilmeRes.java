package br.uece.eescomdevops.filmesapi.domain.dto;

import br.uece.eescomdevops.filmesapi.domain.entity.Filme;
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
