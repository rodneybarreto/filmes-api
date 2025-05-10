package br.uece.eescomdevops.filmesapi.application.ports.inbound.service;

import br.uece.eescomdevops.filmesapi.adapters.inbound.dto.PageRes;
import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;

public interface ReadFilmeServicePort {

    Filme findById(Long id);

    PageRes<Filme> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

}
