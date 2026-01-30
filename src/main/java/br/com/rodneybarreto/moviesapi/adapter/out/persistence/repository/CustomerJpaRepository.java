package br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository;

import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {
}
