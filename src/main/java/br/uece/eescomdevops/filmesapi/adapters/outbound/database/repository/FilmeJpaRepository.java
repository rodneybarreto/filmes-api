package br.uece.eescomdevops.filmesapi.adapters.outbound.database.repository;

import br.uece.eescomdevops.filmesapi.adapters.outbound.database.entity.FilmeJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeJpaRepository extends JpaRepository<FilmeJpaEntity, Long> {

    Page<FilmeJpaEntity> findAll(Pageable pageable);

    Page<FilmeJpaEntity> findByTituloContainsIgnoreCase(String searchTerm, Pageable pageable);

}
