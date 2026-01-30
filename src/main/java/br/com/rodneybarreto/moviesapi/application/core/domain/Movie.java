package br.com.rodneybarreto.moviesapi.application.core.domain;

import java.util.Objects;

public class Movie {

    private Long id;
    private String title;
    private String synopsis;
    private Integer releaseYear;

    public Movie() {}

    public Movie(Long id, String title, String synopsis, Integer releaseYear) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseYear = releaseYear;
    }

    public Movie(String title, String synopsis, Integer releaseYear) {
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
        Objects.requireNonNull(title, "Title cannot be null");
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(
                """
                Movie{"id":%d,"title":"%s","synopsis":"%s","releaseYear":%d}
                """,
                id,
                title,
                synopsis,
                releaseYear
        );
    }
    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public static class MovieBuilder {

        private Long id;
        private String title;
        private String synopsis;
        private Integer releaseYear;

        private MovieBuilder() {
        }

        public MovieBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MovieBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder synopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public MovieBuilder releaseYear(Integer releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Movie build() {
            return new Movie(id, title, synopsis, releaseYear);
        }

    }

}
