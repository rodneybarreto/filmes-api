package br.uece.eescomdevops.filmesapi.domain.entity;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Filme(FilmeDto filmeDto) {
        this.titulo = filmeDto.getTitulo();
        this.sinopse = filmeDto.getSinopse();
        this.anoLancamento = filmeDto.getAnoLancamento();
    }

}
