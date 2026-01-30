package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.in.CreateCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.port.in.ReadCustomerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerController.RESOURCE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    protected static final String RESOURCE = "/v1/customers";

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ReadCustomerUseCase readCustomerUseCase;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
            @Valid @RequestBody CustomerRequest customerRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Customer customer = CustomerRequest.toDomain(customerRequest);
        Customer createdCustomer = createCustomerUseCase.create(customer);
        URI uri = uriBuilder.path(RESOURCE + "/{id}").buildAndExpand(createdCustomer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> findById(@PathVariable long id) {
        Customer customer = readCustomerUseCase.findById(id);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }

}
