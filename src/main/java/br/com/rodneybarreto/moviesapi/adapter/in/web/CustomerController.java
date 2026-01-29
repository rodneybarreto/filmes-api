package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerResponse;
import br.com.rodneybarreto.moviesapi.adapter.mapper.CustomerMapper;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.core.service.CreateCustomerService;
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

    private final CreateCustomerService createCustomerService;
    private final ReadCustomerUseCase readCustomerUseCase;
    private final CustomerMapper mapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
            @Valid @RequestBody CustomerRequest customerRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Customer domain = mapper.toDomain(customerRequest);
        Long customerId = createCustomerService.create(domain);
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
