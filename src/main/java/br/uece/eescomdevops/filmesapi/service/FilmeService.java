package br.uece.eescomdevops.filmesapi.service;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
import br.uece.eescomdevops.filmesapi.domain.dto.FilmeRes;
import br.uece.eescomdevops.filmesapi.domain.entity.Filme;
import br.uece.eescomdevops.filmesapi.repository.FilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Long create(FilmeReq filmeReq) {
        Filme filme = filmeRepository.save(new Filme(filmeReq));
        return filme.getId();
    }

    public Page<FilmeRes> findAll(Pageable pageable) {
        return filmeRepository.findAll(pageable).map(FilmeRes::new);
    }

    public FilmeRes findById(Long id) {
        Filme filme = filmeRepository.getReferenceById(id);
        return new FilmeRes(filme);
    }

    @Transactional
    public void update(Long id, FilmeReq filmeReq) {
        Filme filme = filmeRepository.getReferenceById(id);
        filme.update(filmeReq);
    }

    @Transactional
    public void delete(Long id) {
        Filme filme = filmeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        filmeRepository.delete(filme);
    }

}
