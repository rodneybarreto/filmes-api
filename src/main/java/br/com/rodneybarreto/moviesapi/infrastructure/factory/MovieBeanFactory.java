package br.com.rodneybarreto.moviesapi.infrastructure.factory;

import br.com.rodneybarreto.moviesapi.application.core.usecase.MovieUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MovieBeanFactory {

    @Bean
    public MovieUseCase movieUseCase(MoviePersistence moviePersistence) {
        return new MovieUseCase(moviePersistence);
    }

}
