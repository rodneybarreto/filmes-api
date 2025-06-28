package br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class MovieJpaEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String titulo;

    @Getter
    @Setter
    private String sinopse;

    @Getter
    @Setter
    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

}

