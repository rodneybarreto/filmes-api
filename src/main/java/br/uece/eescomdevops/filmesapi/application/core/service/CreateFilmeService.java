package br.uece.eescomdevops.filmesapi.application.core.service;

import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;
import br.uece.eescomdevops.filmesapi.application.ports.inbound.service.CreateFilmeServicePort;
import br.uece.eescomdevops.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;

public class CreateFilmeService implements CreateFilmeServicePort {

    private final FilmeRepositoryPort filmeRepositoryPort;

    public CreateFilmeService(FilmeRepositoryPort filmeRepositoryPort) {
        this.filmeRepositoryPort = filmeRepositoryPort;
    }

    @Override
    public Long create(Filme filme) {
        Filme filmeSaved = filmeRepositoryPort.save(filme);
        return filmeSaved.getId();
    }

}
