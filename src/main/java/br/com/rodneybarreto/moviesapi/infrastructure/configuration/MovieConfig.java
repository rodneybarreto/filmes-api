package br.com.rodneybarreto.moviesapi.infrastructure.configuration;

import br.com.rodneybarreto.moviesapi.application.core.usecase.CreateMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.core.usecase.DeleteMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.core.usecase.ReadMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.core.usecase.UpdateMovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {

    @Bean
    public CreateMovieUseCase createMovieServiceService(MoviePersistence moviePersistence) {
        return new CreateMovieUseCase(moviePersistence);
    }

    @Bean
    public ReadMovieUseCase readMovieService(MoviePersistence moviePersistence) {
        return new ReadMovieUseCase(moviePersistence);
    }

    @Bean
    public UpdateMovieUseCase updateMovieService(MoviePersistence moviePersistence) {
        return new UpdateMovieUseCase(moviePersistence);
    }

    @Bean
    public DeleteMovieUseCase deleteMovieService(MoviePersistence moviePersistence) {
        return new DeleteMovieUseCase(moviePersistence);
    }

}
