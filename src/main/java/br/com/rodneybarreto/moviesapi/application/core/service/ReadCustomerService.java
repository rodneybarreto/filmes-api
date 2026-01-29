package br.com.rodneybarreto.moviesapi.application.core.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.ReadCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;

public class ReadCustomerService implements ReadCustomerUseCase {

    private final CustomerPersistence customerPersistence;

    public ReadCustomerService(CustomerPersistence customerPersistence) {
        this.customerPersistence = customerPersistence;
    }

    @Override
    public Customer findById(Long id) {
        return customerPersistence.findById(id);
    }

}
