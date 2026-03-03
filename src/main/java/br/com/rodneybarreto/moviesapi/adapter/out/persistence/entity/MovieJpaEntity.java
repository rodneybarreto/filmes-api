package br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "movies")
public class MovieJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String synopsis;

    @Column(name = "release_year")
    private int releaseYear;

    public MovieJpaEntity() {
    }

    public MovieJpaEntity(Long id, String title, String synopsis, int releaseYear) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public static MovieJpaEntity.MovieJpaEntityBuilder builder() {
        return new MovieJpaEntity.MovieJpaEntityBuilder();
    }

    public static class MovieJpaEntityBuilder {

        private Long id;
        private String title;
        private String synopsis;
        private int releaseYear;

        private MovieJpaEntityBuilder() {
        }

        public MovieJpaEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MovieJpaEntityBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MovieJpaEntityBuilder synopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public MovieJpaEntityBuilder releaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public MovieJpaEntity build() {
            return new MovieJpaEntity(id, title, synopsis, releaseYear);
        }
    }

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
        if (movies == null) return Collections.emptyList();
        return movies.stream().map(MovieJpaEntity::toDomain).toList();
    }

}

