package br.com.rodneybarreto.moviesapi.application.ports.outbound.repository;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;

public interface CustomerRepositoryPort {

    Customer save(Customer customer);

    Customer findById(Long id);

}
