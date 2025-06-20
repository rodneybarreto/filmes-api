package br.com.rodneybarreto.filmesapi.adapters.mapper;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeReq;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeRes;
import br.com.rodneybarreto.filmesapi.adapters.outbound.database.entity.FilmeJpaEntity;
import br.com.rodneybarreto.filmesapi.application.core.domain.entity.Filme;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class FilmeMapper {

    public Filme toFilme(FilmeReq filmeReq) {
        Filme filme = new Filme();
        filme.setTitulo(filmeReq.titulo());
        filme.setSinopse(filmeReq.sinopse());
        filme.setAnoLancamento(filmeReq.anoLancamento());
        return filme;
    }

    public Filme toFilme(FilmeJpaEntity filmeJpaEntity) {
        Filme filme = new Filme();
        filme.setId(filmeJpaEntity.getId());
        filme.setTitulo(filmeJpaEntity.getTitulo());
        filme.setSinopse(filmeJpaEntity.getSinopse());
        filme.setAnoLancamento(filmeJpaEntity.getAnoLancamento());
        return filme;
    }

    public List<Filme> toFilmeList(List<FilmeJpaEntity> filmes) {
        if (ObjectUtils.isEmpty(filmes)) return Collections.emptyList();
        return filmes.stream().map(this::toFilme).toList();
    }

    public FilmeJpaEntity toFilmeJpaEntity(Filme filme) {
        FilmeJpaEntity filmeJpaEntity = new FilmeJpaEntity();
        filmeJpaEntity.setTitulo(filme.getTitulo());
        filmeJpaEntity.setSinopse(filme.getSinopse());
        filmeJpaEntity.setAnoLancamento(filme.getAnoLancamento());
        return filmeJpaEntity;
    }

    public FilmeRes toFilmeRes(Filme filme) {
        return new FilmeRes(
                filme.getId(),
                filme.getTitulo(),
                filme.getSinopse(),
                filme.getAnoLancamento()
        );
    }

    public List<FilmeRes> toFilmeResList(List<Filme> filmes) {
        if (ObjectUtils.isEmpty(filmes)) return Collections.emptyList();
        return filmes.stream().map(this::toFilmeRes).toList();
    }

}
