package br.com.rodneybarreto.moviesapi.adapters.outbound.database.repository;

import br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
