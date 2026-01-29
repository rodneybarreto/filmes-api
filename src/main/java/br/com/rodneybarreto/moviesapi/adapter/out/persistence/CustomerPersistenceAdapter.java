package br.com.rodneybarreto.moviesapi.adapter.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.mapper.CustomerMapper;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.CustomerEntity;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository.CustomerJpaRepository;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerPersistence {

    private final CustomerJpaRepository repository;
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
