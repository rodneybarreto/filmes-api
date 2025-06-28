package br.com.rodneybarreto.filmesapi.application.core.usecases;

import br.com.rodneybarreto.filmesapi.application.core.domain.Filme;
import br.com.rodneybarreto.filmesapi.application.ports.inbound.service.UpdateFilmeServicePort;
import br.com.rodneybarreto.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;

public class UpdateFilmeService implements UpdateFilmeServicePort {

    private final FilmeRepositoryPort filmeRepositoryPort;

    public UpdateFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        this.filmeRepositoryPort = filmeRepositoryPort;
    }

    @Override
    public void update(Long id, Filme filme) {
        filmeRepositoryPort.update(id, filme);
    }

}
