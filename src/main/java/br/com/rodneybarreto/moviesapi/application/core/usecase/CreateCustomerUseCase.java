package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateCustomerUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;

public class CreateCustomerUseCase implements CreateCustomerUseCasePort {

    private final CustomerPersistence customerPersistence;

    public CreateCustomerUseCase(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    @Override
    public Customer create(Customer customer) {
        return customerPersistence.create(customer);
    }

}
