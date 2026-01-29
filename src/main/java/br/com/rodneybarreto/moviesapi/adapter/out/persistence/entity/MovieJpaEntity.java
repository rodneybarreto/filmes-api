package br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class MovieJpaEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    private String synopsis;

    @Getter
    @Setter
    @Column(name = "release_year")
    private Integer releaseYear;

    public static MovieJpaEntity of(Movie movie) {
        return new MovieJpaEntity(movie.getId(), movie.getTitle(), movie.getSynopsis(), movie.getReleaseYear());
    }

    public static Movie toDomain(MovieJpaEntity movieJpaEntity) {
        return Movie.builder()
                .id(movieJpaEntity.getId())
                .title(movieJpaEntity.getTitle())
                .synopsis(movieJpaEntity.getSynopsis())
                .releaseYear(movieJpaEntity.getReleaseYear())
                .build();
    }

    public static List<Movie> toDomain(List<MovieJpaEntity> movies) {
        if (ObjectUtils.isEmpty(movies))
            return Collections.emptyList();

        return movies.stream().map(MovieJpaEntity::toDomain).toList();
    }

}

