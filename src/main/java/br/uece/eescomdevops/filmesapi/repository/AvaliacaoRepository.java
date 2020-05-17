package br.uece.eescomdevops.filmesapi.repository;

import br.uece.eescomdevops.filmesapi.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
