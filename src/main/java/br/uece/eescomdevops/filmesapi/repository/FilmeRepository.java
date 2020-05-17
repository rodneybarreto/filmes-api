package br.uece.eescomdevops.filmesapi.repository;

import br.uece.eescomdevops.filmesapi.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
