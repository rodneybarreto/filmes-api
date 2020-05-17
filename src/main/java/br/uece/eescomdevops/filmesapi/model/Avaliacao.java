package br.uece.eescomdevops.filmesapi.model;

import br.uece.eescomdevops.filmesapi.controller.dto.AvaliacaoDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.springframework.util.ObjectUtils.isEmpty;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "pessoa_nome")
    private String pessoaNome;

    @Getter
    @Setter
    @Column(name = "comentario")
    private String comentario;

    @Getter
    @Setter
    @Column(name = "nota")
    private Integer nota;

    @Getter
    @Setter
    @Column(name = "filme_id")
    private Long filmeId;

    public Avaliacao() { }

    public Avaliacao(AvaliacaoDto avaliacaoDto) {
        this.id = isEmpty(avaliacaoDto.getId()) ? null : Long.parseLong(avaliacaoDto.getId());
        this.pessoaNome = avaliacaoDto.getPessoaNome();
        this.comentario = avaliacaoDto.getComentario();
        this.nota = isEmpty(avaliacaoDto.getNota()) ? null : Integer.parseInt(avaliacaoDto.getNota());
    }

}
