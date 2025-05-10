package br.uece.eescomdevops.filmesapi.adapters.outbound.database;

import br.uece.eescomdevops.filmesapi.adapters.inbound.dto.PageRes;
import br.uece.eescomdevops.filmesapi.adapters.mapper.FilmeMapper;
import br.uece.eescomdevops.filmesapi.adapters.outbound.database.entity.FilmeJpaEntity;
import br.uece.eescomdevops.filmesapi.adapters.outbound.database.repository.FilmeJpaRepository;
import br.uece.eescomdevops.filmesapi.application.core.domain.entity.Filme;
import br.uece.eescomdevops.filmesapi.application.ports.outbound.repository.FilmeRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FilmeRepositoryAdapter implements FilmeRepositoryPort {

    private final FilmeJpaRepository filmeJpaRepository;
    private final FilmeMapper mapper;

    @Override
    @Transactional
    public Filme save(Filme filme) {
        FilmeJpaEntity filmeJpaEntity = mapper.toFilmeJpaEntity(filme);
        FilmeJpaEntity filmeSaved = filmeJpaRepository.save(filmeJpaEntity);
        return mapper.toFilme(filmeSaved);
    }

    @Override
    public Filme findById(Long id) {
        return filmeJpaRepository.findById(id)
                .map(mapper::toFilme)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado!"));
    }

    @Override
    public PageRes<Filme> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));

        Page<FilmeJpaEntity> page;
        if (searchTerm == null || searchTerm.isBlank()) {
            page = filmeJpaRepository.findAll(pageable);
        } else {
            page = filmeJpaRepository.findByTituloContainsIgnoreCase(searchTerm, pageable);
        }
        return new PageRes<>(
                mapper.toFilmeList(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    @Transactional
    public void update(Long id, Filme filme) {
        filmeJpaRepository.findById(id).ifPresent(filmeJpaEntity -> {
            filmeJpaEntity.setTitulo(filme.getTitulo());
            filmeJpaEntity.setSinopse(filme.getSinopse());
            filmeJpaEntity.setAnoLancamento(filme.getAnoLancamento());
            filmeJpaRepository.save(filmeJpaEntity);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        filmeJpaRepository.deleteById(id);
    }

}
