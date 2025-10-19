package br.com.rodneybarreto.moviesapi.application.core.usecases;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.CreateCustomerPort;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.CustomerRepositoryPort;

public class CreateCustomerUseCase implements CreateCustomerPort {

    private final CustomerRepositoryPort repositoryPort;

    public CreateCustomerUseCase(CustomerRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Long create(Customer customer) {
        Customer customerSaved = repositoryPort.save(customer);
        return customerSaved.getId();
    }

}
