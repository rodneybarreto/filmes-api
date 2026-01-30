package br.com.rodneybarreto.moviesapi.infrastructure.configuration;

import br.com.rodneybarreto.moviesapi.application.core.usecase.CreateCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.core.usecase.ReadCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerPersistence customerPersistence) {
        return new CreateCustomerUseCase(customerPersistence);
    }

    @Bean
    public ReadCustomerUseCase readCustomerUseCase(CustomerPersistence customerPersistence) {
        return new ReadCustomerUseCase(customerPersistence);
    }

}
