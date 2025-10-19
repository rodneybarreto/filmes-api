package br.com.rodneybarreto.moviesapi.adapters.outbound.database;

import br.com.rodneybarreto.moviesapi.adapters.mapper.CustomerMapper;
import br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity.CustomerEntity;
import br.com.rodneybarreto.moviesapi.adapters.outbound.database.repository.CustomerRepository;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.CustomerRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity entitySaved = repository.save(entity);
        return mapper.toDomain(entitySaved);
    }

    @Override
    public Customer findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
