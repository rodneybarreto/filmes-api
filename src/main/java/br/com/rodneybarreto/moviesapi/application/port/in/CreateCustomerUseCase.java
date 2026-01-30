package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;

public interface CreateCustomerUseCase {

    Customer create(Customer customer);

}
