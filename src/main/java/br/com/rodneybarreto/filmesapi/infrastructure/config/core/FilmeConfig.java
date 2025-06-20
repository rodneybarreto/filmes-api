package br.com.rodneybarreto.filmesapi.infrastructure.config.core;

import br.com.rodneybarreto.filmesapi.application.core.service.CreateFilmeService;
import br.com.rodneybarreto.filmesapi.application.core.service.DeleteFilmeService;
import br.com.rodneybarreto.filmesapi.application.core.service.ReadFilmeService;
import br.com.rodneybarreto.filmesapi.application.core.service.UpdateFilmeService;
import br.com.rodneybarreto.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilmeConfig {

    @Bean
    public CreateFilmeService createFilmeServiceService(FilmeRepositoryPort filmeRepositoryPort) {
        return new CreateFilmeService(filmeRepositoryPort);
    }

    @Bean
    public ReadFilmeService readFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        return new ReadFilmeService(filmeRepositoryPort);
    }

    @Bean
    public UpdateFilmeService updateFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        return new UpdateFilmeService(filmeRepositoryPort);
    }

    @Bean
    public DeleteFilmeService deleteFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        return new DeleteFilmeService(filmeRepositoryPort);
    }

}
