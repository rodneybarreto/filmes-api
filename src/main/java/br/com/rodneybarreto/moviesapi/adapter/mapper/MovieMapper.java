package br.com.rodneybarreto.moviesapi.adapter.mapper;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieReq;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieRes;
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
