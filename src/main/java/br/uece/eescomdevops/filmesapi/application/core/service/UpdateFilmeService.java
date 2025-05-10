package br.uece.eescomdevops.filmesapi.application.core.service;

import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;
import br.uece.eescomdevops.filmesapi.application.ports.inbound.service.UpdateFilmeServicePort;
import br.uece.eescomdevops.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;

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
