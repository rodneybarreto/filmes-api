package br.com.rodneybarreto.moviesapi.adapter.out.web;

import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.out.web.FindRestClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class OmdbClientAdapter implements FindRestClientPort {

    /**
     * {"Title":"The Bourne Identity","Year":"2002","Rated":"PG-13","Released":"14 Jun 2002","Runtime":"119 min",
     * "Genre":"Action, Mystery, Thriller","Director":"Doug Liman","Writer":"Tony Gilroy, William Blake Herron,
     * Robert Ludlum","Actors":"Franka Potente, Matt Damon, Chris Cooper","Plot":"When a body is recovered at sea
     * ]still alive, the mystery man (Damon) seems to have forgotten everything in life, including who he was.
     * Eventually he begins to remember smaller details in life and soon finds out that his name was Jason Bourne.
     * What he doesn't like is the gun and fake passports belonging to him. Now Bourne, and his new friend, Marie
     * Helena Kreutz (Potente) travel from country to country in search of his new identity. But someone is not
     * happy to see him alive, and is frantically trying to track him down.","Language":"English, French, German,
     * Dutch, Italian, Yoruba","Country":"Czech Republic, Germany, United States","Awards":"3 wins & 6 nominations
     * total","Poster":"https://m.media-amazon.com/images/M/MV5BYTk1ZTcyMWMtMWUxYS00MmEzLTlmODYtOTk1MGRjOTg1ZjlmXkEyXkFqcGc@._V1_SX300.jpg",
     * "Ratings":[{"Source":"Internet Movie Database","Value":"7.8/10"},{"Source":"Rotten Tomatoes","Value":"84%"},
     * {"Source":"Metacritic","Value":"68/100"}],"Metascore":"68","imdbRating":"7.8","imdbVotes":"596,003",
     * "imdbID":"tt0258463","Type":"movie","DVD":"N/A","BoxOffice":"$121,661,683","Production":"N/A","Website":"N/A",
     * "Response":"True"}
     */

    private final RestClient restClient;

    @Override
    public Movie findByTitle(String title) {
        return restClient.get()
                .uri("&t={}", title)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Movie.class);
    }

    @Override
    public Movie findById(String id) {
        return restClient.get()
                .uri("&i={}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Movie.class);
    }

}
