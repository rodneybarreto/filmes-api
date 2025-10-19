package br.com.rodneybarreto.moviesapi.infrastructure.factories;

import br.com.rodneybarreto.moviesapi.application.core.usecases.CreateCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.core.usecases.ReadCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerFactory {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new CreateCustomerUseCase(customerRepositoryPort);
    }

    @Bean
    public ReadCustomerUseCase readCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new ReadCustomerUseCase(customerRepositoryPort);
    }

}
