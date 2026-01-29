package br.com.rodneybarreto.moviesapi.application.port.out.persistence;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;

public interface CustomerPersistence {

    Customer save(Customer customer);

    Customer findById(Long id);

}
