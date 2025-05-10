package br.uece.eescomdevops.filmesapi.application.ports.inbound.service;

import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;

public interface UpdateFilmeServicePort {

    void update(Long id, Filme filme);

}

