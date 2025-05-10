package br.uece.eescomdevops.filmesapi.application.ports.outbound.repository;

import br.uece.eescomdevops.filmesapi.adapters.inbound.dto.PageRes;
import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;

public interface FilmeRepositoryPort {

    Filme save(Filme filme);

    Filme findById(Long id);

    PageRes<Filme> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

    void update(Long id, Filme filme);

    void deleteById(Long id);

}
