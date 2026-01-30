package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.CustomerUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;

public class CustomerUseCase implements CustomerUseCasePort {

    private final CustomerPersistence customerPersistence;

    public CustomerUseCase(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerPersistence.create(customer);
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerPersistence.findById(id);
    }

}
