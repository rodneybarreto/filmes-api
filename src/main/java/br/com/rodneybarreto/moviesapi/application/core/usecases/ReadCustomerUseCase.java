package br.com.rodneybarreto.moviesapi.application.core.usecases;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.ports.inbound.service.ReadCustomerPort;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.CustomerRepositoryPort;

public class ReadCustomerUseCase implements ReadCustomerPort {

    private final CustomerRepositoryPort customerRepositoryPort;

    public ReadCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepositoryPort.findById(id);
    }

}
