package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.CustomerUseCasePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerControllerAdapter.CUSTOMER_RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerControllerAdapter {

    public static final String CUSTOMER_RESOURCE = "/v1/customers";

    private final CustomerUseCasePort customerUseCasePort;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
            @Valid @RequestBody CustomerRequest customerRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Customer customer = CustomerRequest.toDomain(customerRequest);
        Customer createdCustomer = customerUseCasePort.createCustomer(customer);
        URI uri = uriBuilder.path(CUSTOMER_RESOURCE + "/{id}").buildAndExpand(createdCustomer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> findOne(@PathVariable long id) {
        Customer customer = customerUseCasePort.findCustomerById(id);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }

}
