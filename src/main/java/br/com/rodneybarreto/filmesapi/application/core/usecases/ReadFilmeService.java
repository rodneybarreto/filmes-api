package br.com.rodneybarreto.filmesapi.application.core.usecases;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.filmesapi.application.core.domain.Filme;
import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.ReadFilmeServicePort;
import br.com.rodneybarreto.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;

public class ReadFilmeService implements ReadFilmeServicePort {

    private final FilmeRepositoryPort filmeRepositoryPort;

    public ReadFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        this.filmeRepositoryPort = filmeRepositoryPort;
    }

    @Override
    public Filme findById(Long id) {
        return filmeRepositoryPort.findById(id);
    }

    @Override
    public PageRes<Filme> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        return filmeRepositoryPort.findAll(pageNumber, pageSize, sortOrder, sortBy, searchTerm);
    }

}
