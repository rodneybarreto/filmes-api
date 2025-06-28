package br.com.rodneybarreto.filmesapi.application.core.usecases;

import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.DeleteFilmeServicePort;
import br.com.rodneybarreto.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;

public class DeleteFilmeService implements DeleteFilmeServicePort {

    private final FilmeRepositoryPort filmeRepositoryPort;

    public DeleteFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        this.filmeRepositoryPort = filmeRepositoryPort;
    }

    @Override
    public void delete(Long id) {
        filmeRepositoryPort.deleteById(id);
    }

}
