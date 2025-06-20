package br.com.rodneybarreto.filmesapi.application.ports.inbound.service;

import br.com.rodneybarreto.filmesapi.application.core.domain.entity.Filme;

public interface UpdateFilmeServicePort {

    void update(Long id, Filme filme);

}

