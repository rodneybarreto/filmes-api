package br.uece.eescomdevops.filmesapi.model;

import br.uece.eescomdevops.filmesapi.controller.dto.FilmeDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "titulo")
    private String titulo;

    @Getter
    @Setter
    @Column(name = "sinopse")
    private String sinopse;

    @Getter
    @Setter
    @Column(name = "ano_lancamento")
    private String anoLancamento;

    @Getter
    @Setter
    @Column(name = "produtores")
    private String produtores;

    @Getter
    @Setter
    @Column(name = "protagonistas")
    private String protagonistas;

    @Getter
    @Setter
    @OneToMany(mappedBy = "filmeId")
    private List<Avaliacao> avaliacoes;

    public Filme() { }

    public Filme(FilmeDto filmeDto) {
        this.id = isEmpty(filmeDto.getId()) ? null : Long.parseLong(filmeDto.getId());
        this.titulo = filmeDto.getTitulo();
        this.sinopse = filmeDto.getSinopse();
        this.anoLancamento = filmeDto.getAnoLancamento();
        this.produtores = filmeDto.getProdutores();
        this.protagonistas = filmeDto.getProtagonistas();
    }

}
