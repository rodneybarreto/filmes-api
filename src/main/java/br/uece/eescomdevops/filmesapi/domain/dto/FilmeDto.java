package br.uece.eescomdevops.filmesapi.domain.dto;

import br.uece.eescomdevops.filmesapi.domain.entity.Filme;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FilmeDto {

    private Long id;

    @NotBlank
    private String titulo;

    private String sinopse;

    @JsonAlias("ano_lancamento")
    private Integer anoLancamento;

    public FilmeDto(){}

    public FilmeDto(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.sinopse = filme.getSinopse();
        this.anoLancamento = filme.getAnoLancamento();
    }

}
