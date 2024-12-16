package br.uece.eescomdevops.filmesapi.domain.entity;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@Getter
@Entity
@Table(name = "filmes")
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String sinopse;

    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

    public Filme(FilmeReq filmeReq) {
        this.titulo = filmeReq.titulo();
        this.sinopse = filmeReq.sinopse();
        this.anoLancamento = filmeReq.anoLancamento();
    }

    public void update(@Valid FilmeReq filmeReq) {
        if (nonNull(filmeReq.titulo())) {
            this.titulo = filmeReq.titulo();
        }
        if (nonNull(filmeReq.sinopse())) {
            this.sinopse = filmeReq.sinopse();
        }
        if (nonNull(filmeReq.anoLancamento())) {
            this.anoLancamento = filmeReq.anoLancamento();
        }
    }

}
