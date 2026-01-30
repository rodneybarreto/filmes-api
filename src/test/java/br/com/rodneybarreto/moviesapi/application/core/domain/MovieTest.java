package br.com.rodneybarreto.moviesapi.application.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovieTest {

    @Test
    @DisplayName("Should create movie using all args constructor")
    void shouldCreateMovieWithAllArgsConstructor() {
        Long id = 1L;
        String title = "Inception";
        String synopsis = "A thief who steals corporate secrets through the use of dream-sharing technology.";
        Integer releaseYear = 2010;

        Movie movie = new Movie(id, title, synopsis, releaseYear);

        assertThat(movie.getId()).isEqualTo(id);
        assertThat(movie.getTitle()).isEqualTo(title);
        assertThat(movie.getSynopsis()).isEqualTo(synopsis);
        assertThat(movie.getReleaseYear()).isEqualTo(releaseYear);
    }

    @Test
    @DisplayName("Should create movie using constructor without ID")
    void shouldCreateMovieWithoutIdConstructor() {
        String title = "The Matrix";
        String synopsis = "A computer hacker learns from mysterious rebels about the true nature of his reality.";
        Integer releaseYear = 1999;

        Movie movie = new Movie(title, synopsis, releaseYear);

        assertThat(movie.getId()).isNull();
        assertThat(movie.getTitle()).isEqualTo(title);
        assertThat(movie.getSynopsis()).isEqualTo(synopsis);
        assertThat(movie.getReleaseYear()).isEqualTo(releaseYear);
    }

    @Test
    @DisplayName("Should create movie using builder")
    void shouldCreateMovieWithBuilder() {
        Long id = 2L;
        String title = "Interstellar";
        String synopsis = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.";
        Integer releaseYear = 2014;

        Movie movie = Movie.builder()
                .id(id)
                .title(title)
                .synopsis(synopsis)
                .releaseYear(releaseYear)
                .build();

        assertThat(movie.getId()).isEqualTo(id);
        assertThat(movie.getTitle()).isEqualTo(title);
        assertThat(movie.getSynopsis()).isEqualTo(synopsis);
        assertThat(movie.getReleaseYear()).isEqualTo(releaseYear);
    }

    @Test
    @DisplayName("Should update fields using setters")
    void shouldUpdateFieldsUsingSetters() {
        Movie movie = new Movie();
        
        movie.setId(3L);
        movie.setTitle("Dune");
        movie.setSynopsis("Feature adaptation of Frank Herbert's science fiction novel.");
        movie.setReleaseYear(2021);

        assertThat(movie.getId()).isEqualTo(3L);
        assertThat(movie.getTitle()).isEqualTo("Dune");
        assertThat(movie.getSynopsis()).isEqualTo("Feature adaptation of Frank Herbert's science fiction novel.");
        assertThat(movie.getReleaseYear()).isEqualTo(2021);
    }

    @Test
    @DisplayName("Should throw exception when setting null title")
    void shouldThrowExceptionWhenTitleIsNull() {
        Movie movie = new Movie();
        
        assertThatThrownBy(() -> movie.setTitle(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Title cannot be null");
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void shouldTestEqualsAndHashCode() {
        Movie movie1 = new Movie(1L, "Movie A", "Synopsis A", 2000);
        Movie movie2 = new Movie(1L, "Movie B", "Synopsis B", 2001); // Same ID
        Movie movie3 = new Movie(2L, "Movie A", "Synopsis A", 2000); // Different ID

        assertThat(movie1).isEqualTo(movie2);
        assertThat(movie1).hasSameHashCodeAs(movie2);
        
        assertThat(movie1).isNotEqualTo(movie3);
        assertThat(movie1).isNotEqualTo(null);
        assertThat(movie1).isNotEqualTo(new Object());
    }

    @Test
    @DisplayName("Should test toString")
    void shouldTestToString() {
        Movie movie = new Movie(1L, "Test Movie", "Test Synopsis", 2023);
        
        String expectedString = """
            Movie{"id":1,"title":"Test Movie","synopsis":"Test Synopsis","releaseYear":2023}
            """;
        
        assertThat(movie.toString()).isEqualToIgnoringNewLines(expectedString);
    }

}
