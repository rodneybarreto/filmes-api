package br.com.rodneybarreto.filmesapi.application.ports.outbound.repository;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.filmesapi.application.core.domain.Filme;

public interface FilmeRepositoryPort {

    Filme save(Filme filme);

    Filme findById(Long id);

    PageRes<Filme> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm);

    void update(Long id, Filme filme);

    void deleteById(Long id);

}
