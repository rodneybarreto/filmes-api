package br.com.rodneybarreto.moviesapi.infrastructure.configuration;

import br.com.rodneybarreto.moviesapi.application.core.usecase.MovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {

    @Bean
    public MovieUseCase movieUseCase(MoviePersistence moviePersistence) {
        return new MovieUseCase(moviePersistence);
    }

}
