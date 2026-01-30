package br.com.rodneybarreto.moviesapi.infrastructure.configuration;

import br.com.rodneybarreto.moviesapi.application.core.usecase.CustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerUseCase customerUseCase(CustomerPersistence customerPersistence) {
        return new CustomerUseCase(customerPersistence);
    }

}
