package br.com.rodneybarreto.moviesapi.adapters.outbound.database.repository;

import br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity.MovieJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieJpaEntity, Long> {

    Page<MovieJpaEntity> findAll(Pageable pageable);

    Page<MovieJpaEntity> findByTituloContainsIgnoreCase(String searchTerm, Pageable pageable);

}
