package br.com.rodneybarreto.moviesapi.infrastructure.factory;

import br.com.rodneybarreto.moviesapi.application.core.service.CreateMovieService;
import br.com.rodneybarreto.moviesapi.application.core.service.DeleteMovieService;
import br.com.rodneybarreto.moviesapi.application.core.service.ReadMovieService;
import br.com.rodneybarreto.moviesapi.application.core.service.UpdateMovieService;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieFactory {

    @Bean
    public CreateMovieService createMovieServiceService(MoviePersistence moviePersistence) {
        return new CreateMovieService(moviePersistence);
    }

    @Bean
    public ReadMovieService readMovieService(MoviePersistence moviePersistence) {
        return new ReadMovieService(moviePersistence);
    }

    @Bean
    public UpdateMovieService updateMovieService(MoviePersistence moviePersistence) {
        return new UpdateMovieService(moviePersistence);
    }

    @Bean
    public DeleteMovieService deleteMovieService(MoviePersistence moviePersistence) {
        return new DeleteMovieService(moviePersistence);
    }

}
