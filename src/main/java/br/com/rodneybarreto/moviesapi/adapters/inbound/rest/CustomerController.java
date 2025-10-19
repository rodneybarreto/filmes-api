package br.com.rodneybarreto.moviesapi.adapters.inbound.rest;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.CustomerRequest;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.CustomerResponse;
import br.com.rodneybarreto.moviesapi.adapters.mapper.CustomerMapper;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.core.usecases.CreateCustomerUseCase;
import br.com.rodneybarreto.moviesapi.application.core.usecases.ReadCustomerUseCase;
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
    private final CustomerMapper mapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
            @Valid @RequestBody CustomerRequest customerRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Customer domain = mapper.toDomain(customerRequest);
        Long customerId = createCustomerUseCase.create(domain);
        URI uri = uriComponentsBuilder.path(RESOURCE + "/{id}").buildAndExpand(customerId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        Customer customer = readCustomerUseCase.findById(id);
        CustomerResponse customerResponse = mapper.toResponse(customer);
        return ResponseEntity.ok(customerResponse);
    }

}
