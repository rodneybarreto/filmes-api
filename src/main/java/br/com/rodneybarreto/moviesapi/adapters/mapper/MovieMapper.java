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
                .title(movieReq.title())
                .synopsis(movieReq.synopsis())
                .releaseYear(movieReq.releaseYear())
                .build();
    }

    public Movie toDomain(MovieJpaEntity movieJpaEntity) {
        return Movie.builder()
                .id(movieJpaEntity.getId())
                .title(movieJpaEntity.getTitle())
                .synopsis(movieJpaEntity.getSynopsis())
                .releaseYear(movieJpaEntity.getReleaseYear())
                .build();
    }

    public List<Movie> toDomain(List<MovieJpaEntity> movies) {
        if (ObjectUtils.isEmpty(movies)) return Collections.emptyList();
        return movies.stream().map(this::toDomain).toList();
    }

    public MovieJpaEntity toEntity(Movie movie) {
        MovieJpaEntity movieJpaEntity = new MovieJpaEntity();
        movieJpaEntity.setTitle(movie.getTitle());
        movieJpaEntity.setSynopsis(movie.getSynopsis());
        movieJpaEntity.setReleaseYear(movie.getReleaseYear());
        return movieJpaEntity;
    }

    public MovieRes toResponse(Movie movie) {
        return new MovieRes(
                movie.getId(),
                movie.getTitle(),
                movie.getSynopsis(),
                movie.getReleaseYear()
        );
    }

    public List<MovieRes> toResponse(List<Movie> movies) {
        if (ObjectUtils.isEmpty(movies)) return Collections.emptyList();
        return movies.stream().map(this::toResponse).toList();
    }

}
