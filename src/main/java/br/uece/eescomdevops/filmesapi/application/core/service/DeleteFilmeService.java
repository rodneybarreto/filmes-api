package br.uece.eescomdevops.filmesapi.application.core.service;

import br.uece.eescomdevops.filmesapi.application.ports.inbound.service.DeleteFilmeServicePort;
import br.uece.eescomdevops.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;

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
