package br.uece.eescomdevops.filmesapi.controller.dto;

import br.uece.eescomdevops.filmesapi.model.Avaliacao;
import lombok.Data;

@Data
public class AvaliacaoDto {

    private String id;
    private String pessoaNome;
    private String comentario;
    private String nota;
    private String filmeId;

    public AvaliacaoDto() { }

    public AvaliacaoDto(Avaliacao avaliacao) {
        this.id = avaliacao.getId().toString();
        this.pessoaNome = avaliacao.getPessoaNome();
        this.comentario = avaliacao.getComentario();
        this.nota = avaliacao.getNota().toString();
        this.filmeId = avaliacao.getFilmeId().toString();
    }

}
