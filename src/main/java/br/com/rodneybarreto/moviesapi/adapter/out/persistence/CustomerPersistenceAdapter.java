package br.com.rodneybarreto.moviesapi.adapter.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.CustomerEntity;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository.CustomerJpaRepository;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerPersistence {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    @Transactional
    public Customer create(Customer customer) {
        CustomerEntity entity = CustomerEntity.of(customer);
        CustomerEntity entitySaved = customerJpaRepository.save(entity);
        return CustomerEntity.toDomain(entitySaved);
    }

    @Override
    public Customer findById(Long id) {
        return customerJpaRepository.findById(id)
                .map(CustomerEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

}
