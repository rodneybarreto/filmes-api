package br.com.rodneybarreto.filmesapi.application.ports.inbound.service;

import br.com.rodneybarreto.filmesapi.application.core.domain.entity.Filme;

public interface CreateFilmeServicePort {

    Long create(Filme filme);

}
