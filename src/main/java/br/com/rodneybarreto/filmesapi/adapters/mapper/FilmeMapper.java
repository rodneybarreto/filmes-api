package br.com.rodneybarreto.filmesapi.adapters.mapper;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeReq;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeRes;
import br.com.rodneybarreto.filmesapi.adapters.outbound.database.entity.FilmeJpaEntity;
import br.com.rodneybarreto.filmesapi.application.core.domain.Filme;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class FilmeMapper {

    public Filme toDomain(FilmeReq filmeReq) {
        return Filme.builder()
                .titulo(filmeReq.titulo())
                .sinopse(filmeReq.sinopse())
                .anoLancamento(filmeReq.anoLancamento())
                .build();
    }

    public Filme toDomain(FilmeJpaEntity filmeJpaEntity) {
        return Filme.builder()
                .id(filmeJpaEntity.getId())
                .titulo(filmeJpaEntity.getTitulo())
                .sinopse(filmeJpaEntity.getSinopse())
                .anoLancamento(filmeJpaEntity.getAnoLancamento())
                .build();
    }

    public List<Filme> toDomain(List<FilmeJpaEntity> filmes) {
        if (ObjectUtils.isEmpty(filmes)) return Collections.emptyList();
        return filmes.stream().map(this::toDomain).toList();
    }

    public FilmeJpaEntity toEntity(Filme filme) {
        FilmeJpaEntity filmeJpaEntity = new FilmeJpaEntity();
        filmeJpaEntity.setTitulo(filme.getTitulo());
        filmeJpaEntity.setSinopse(filme.getSinopse());
        filmeJpaEntity.setAnoLancamento(filme.getAnoLancamento());
        return filmeJpaEntity;
    }

    public FilmeRes toResponse(Filme filme) {
        return new FilmeRes(
                filme.getId(),
                filme.getTitulo(),
                filme.getSinopse(),
                filme.getAnoLancamento()
        );
    }

    public List<FilmeRes> toResponse(List<Filme> filmes) {
        if (ObjectUtils.isEmpty(filmes)) return Collections.emptyList();
        return filmes.stream().map(this::toResponse).toList();
    }

}
