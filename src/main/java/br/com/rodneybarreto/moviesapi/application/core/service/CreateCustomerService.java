package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;

public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerPersistence repositoryPort;

    public CreateCustomerService(CustomerPersistence repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Long create(Customer customer) {
        Customer customerSaved = repositoryPort.save(customer);
        return customerSaved.getId();
    }

}
