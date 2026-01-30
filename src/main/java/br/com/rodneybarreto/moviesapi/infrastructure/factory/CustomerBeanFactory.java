package br.com.rodneybarreto.moviesapi.infrastructure.factory;

import br.com.rodneybarreto.moviesapi.application.core.usecase.CustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomerBeanFactory {

    @Bean
    public CustomerUseCase customerUseCase(CustomerPersistence customerPersistence) {
        return new CustomerUseCase(customerPersistence);
    }

}
