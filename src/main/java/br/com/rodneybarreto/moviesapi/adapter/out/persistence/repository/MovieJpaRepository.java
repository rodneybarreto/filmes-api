package br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository;

import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.MovieJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<MovieJpaEntity, Long> {

    Page<MovieJpaEntity> findAll(Pageable pageable);

    Page<MovieJpaEntity> findByTitleContainsIgnoreCase(String searchTerm, Pageable pageable);

}
