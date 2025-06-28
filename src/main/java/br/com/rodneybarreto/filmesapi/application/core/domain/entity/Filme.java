package br.com.rodneybarreto.filmesapi.application.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    private Long id;

    private String titulo;

    private String sinopse;

    private Integer anoLancamento;

}
