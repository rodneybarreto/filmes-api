package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;

public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerPersistence customerPersistence;

    public CreateCustomerService(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    @Override
    public Customer create(Customer customer) {
        return customerPersistence.create(customer);
    }

}
