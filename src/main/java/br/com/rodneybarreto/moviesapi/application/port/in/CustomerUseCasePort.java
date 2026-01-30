package br.com.rodneybarreto.moviesapi.application.port.in;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;

public interface CustomerUseCasePort {

    Customer createCustomer(Customer customer);

    Customer findCustomerById(long id);

}
