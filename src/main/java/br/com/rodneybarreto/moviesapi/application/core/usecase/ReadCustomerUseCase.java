package br.com.rodneybarreto.moviesapi.application.core.usecase;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.ReadCustomerUseCasePort;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;

public class ReadCustomerUseCase implements ReadCustomerUseCasePort {

    private final CustomerPersistence customerPersistence;

    public ReadCustomerUseCase(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    @Override
    public Customer findById(long id) {
        return customerPersistence.findById(id);
    }

}
