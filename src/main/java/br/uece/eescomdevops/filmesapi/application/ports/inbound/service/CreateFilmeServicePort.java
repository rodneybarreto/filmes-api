package br.uece.eescomdevops.filmesapi.application.ports.inbound.service;

import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;

public interface CreateFilmeServicePort {

    Long create(Filme filme);

}
