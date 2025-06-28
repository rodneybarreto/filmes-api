package br.com.rodneybarreto.filmesapi.application.ports.inbound.service;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.filmesapi.application.core.domain.Filme;

public interface ReadFilmeServicePort {

    Filme findById(Long id);

    PageRes<Filme> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

}
