package br.com.rodneybarreto.moviesapi.adapters.outbound.database;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.moviesapi.adapters.mapper.MovieMapper;
import br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity.MovieJpaEntity;
import br.com.rodneybarreto.moviesapi.adapters.outbound.database.repository.MovieJpaRepository;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.ports.outbound.repository.MovieRepositoryPort;
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
public class MovieRepositoryAdapter implements MovieRepositoryPort {

    private final MovieJpaRepository movieJpaRepository;
    private final MovieMapper mapper;

    @Override
    @Transactional
    public Movie save(Movie movie) {
        MovieJpaEntity movieJpaEntity = mapper.toEntity(movie);
        MovieJpaEntity movieSaved = movieJpaRepository.save(movieJpaEntity);
        return mapper.toDomain(movieSaved);
    }

    @Override
    public Movie findById(Long id) {
        return movieJpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado!"));
    }

    @Override
    public PageRes<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));

        Page<MovieJpaEntity> page;
        if (searchTerm == null || searchTerm.isBlank()) {
            page = movieJpaRepository.findAll(pageable);
        } else {
            page = movieJpaRepository.findByTitleContainsIgnoreCase(searchTerm, pageable);
        }
        return new PageRes<>(
                mapper.toDomain(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    @Transactional
    public void update(Long id, Movie movie) {
        movieJpaRepository.findById(id).ifPresent(movieJpaEntity -> {
            movieJpaEntity.setTitle(movie.getTitle());
            movieJpaEntity.setSynopsis(movie.getSynopsis());
            movieJpaEntity.setReleaseYear(movie.getReleaseYear());
            movieJpaRepository.save(movieJpaEntity);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        movieJpaRepository.deleteById(id);
    }

}
