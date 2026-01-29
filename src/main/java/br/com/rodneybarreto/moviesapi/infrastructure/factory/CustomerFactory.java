package br.com.rodneybarreto.moviesapi.infrastructure.factory;

import br.com.rodneybarreto.moviesapi.application.core.service.CreateCustomerService;
import br.com.rodneybarreto.moviesapi.application.core.service.ReadCustomerService;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerFactory {

    @Bean
    public CreateCustomerService createCustomerUseCase(CustomerPersistence customerPersistence) {
        return new CreateCustomerService(customerPersistence);
    }

    @Bean
    public ReadCustomerService readCustomerUseCase(CustomerPersistence customerPersistence) {
        return new ReadCustomerService(customerPersistence);
    }

}
