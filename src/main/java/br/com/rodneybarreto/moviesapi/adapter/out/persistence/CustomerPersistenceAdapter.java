package br.com.rodneybarreto.moviesapi.adapter.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.CustomerJpaEntity;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository.CustomerJpaRepository;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.CustomerPersistence;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceAdapter implements CustomerPersistence {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerPersistenceAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    @Transactional
    public Customer create(Customer customer) {
        CustomerJpaEntity entity = CustomerJpaEntity.of(customer);
        CustomerJpaEntity entitySaved = customerJpaRepository.save(entity);
        return CustomerJpaEntity.toDomain(entitySaved);
    }

    @Override
    public Customer findById(long id) {
        return customerJpaRepository.findById(id)
                .map(CustomerJpaEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

}
