package br.uece.eescomdevops.filmesapi.controller.dto;

import br.uece.eescomdevops.filmesapi.model.Filme;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class FilmeDto {

    private String id;
    private String titulo;
    private String sinopse;
    private String anoLancamento;
    private String produtores;
    private String protagonistas;
    private List<AvaliacaoDto> avaliacoes;

    public FilmeDto() { }

    public FilmeDto(Filme filme) {
        this.id = filme.getId().toString();
        this.titulo = filme.getTitulo();
        this.sinopse = filme.getSinopse();
        this.anoLancamento = filme.getAnoLancamento();
        this.produtores = filme.getProdutores();
        this.protagonistas = filme.getProtagonistas();
        this.avaliacoes = filme.getAvaliacoes().stream().map(AvaliacaoDto::new).collect(toList());
    }

}
