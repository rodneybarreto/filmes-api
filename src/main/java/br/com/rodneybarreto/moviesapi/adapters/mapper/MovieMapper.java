package br.com.rodneybarreto.moviesapi.adapters.mapper;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieReq;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieRes;
import br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity.MovieJpaEntity;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class MovieMapper {

    public Movie toDomain(MovieReq movieReq) {
        return Movie.builder()
                .titulo(movieReq.titulo())
                .sinopse(movieReq.sinopse())
                .anoLancamento(movieReq.anoLancamento())
                .build();
    }

    public Movie toDomain(MovieJpaEntity movieJpaEntity) {
        return Movie.builder()
                .id(movieJpaEntity.getId())
                .titulo(movieJpaEntity.getTitulo())
                .sinopse(movieJpaEntity.getSinopse())
                .anoLancamento(movieJpaEntity.getAnoLancamento())
                .build();
    }

    public List<Movie> toDomain(List<MovieJpaEntity> filmes) {
        if (ObjectUtils.isEmpty(filmes)) return Collections.emptyList();
        return filmes.stream().map(this::toDomain).toList();
    }

    public MovieJpaEntity toEntity(Movie movie) {
        MovieJpaEntity movieJpaEntity = new MovieJpaEntity();
        movieJpaEntity.setTitulo(movie.getTitulo());
        movieJpaEntity.setSinopse(movie.getSinopse());
        movieJpaEntity.setAnoLancamento(movie.getAnoLancamento());
        return movieJpaEntity;
    }

    public MovieRes toResponse(Movie movie) {
        return new MovieRes(
                movie.getId(),
                movie.getTitulo(),
                movie.getSinopse(),
                movie.getAnoLancamento()
        );
    }

    public List<MovieRes> toResponse(List<Movie> movies) {
        if (ObjectUtils.isEmpty(movies)) return Collections.emptyList();
        return movies.stream().map(this::toResponse).toList();
    }

}
