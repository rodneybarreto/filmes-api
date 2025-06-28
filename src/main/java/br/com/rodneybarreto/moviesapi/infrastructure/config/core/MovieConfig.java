package br.com.rodneybarreto.moviesapi.infrastructure.config.core;

import br.com.rodneybarreto.moviesapi.application.core.usecases.CreateMovieService;
import br.com.rodneybarreto.moviesapi.application.core.usecases.DeleteMovieService;
import br.com.rodneybarreto.moviesapi.application.core.usecases.ReadMovieService;
import br.com.rodneybarreto.moviesapi.application.core.usecases.UpdateMovieService;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.MovieRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {

    @Bean
    public CreateMovieService createMovieServiceService(MovieRepositoryPort movieRepositoryPort) {
        return new CreateMovieService(movieRepositoryPort);
    }

    @Bean
    public ReadMovieService readMovieService(MovieRepositoryPort movieRepositoryPort) {
        return new ReadMovieService(movieRepositoryPort);
    }

    @Bean
    public UpdateMovieService updateMovieService(MovieRepositoryPort movieRepositoryPort) {
        return new UpdateMovieService(movieRepositoryPort);
    }

    @Bean
    public DeleteMovieService deleteMovieService(MovieRepositoryPort movieRepositoryPort) {
        return new DeleteMovieService(movieRepositoryPort);
    }

}
