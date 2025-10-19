package br.com.rodneybarreto.moviesapi.application.ports.inbound.service;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;

public interface CreateCustomerPort {

    Long create(Customer customer);

}
