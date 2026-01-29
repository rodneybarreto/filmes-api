package br.com.rodneybarreto.moviesapi.adapter.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageRes;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.MovieJpaEntity;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository.MovieJpaRepository;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.application.port.out.persistence.MoviePersistence;
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
public class MoviePersistenceAdapter implements MoviePersistence {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    @Transactional
    public Movie create(Movie movie) {
        MovieJpaEntity entity = MovieJpaEntity.of(movie);
        MovieJpaEntity createdEntity = movieJpaRepository.save(entity);
        return MovieJpaEntity.toDomain(createdEntity);
    }

    @Override
    public Movie findById(Long id) {
        return movieJpaRepository.findById(id)
                .map(MovieJpaEntity::toDomain)
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
                MovieJpaEntity.toDomain(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    @Transactional
    public void update(Long id, Movie movie) {
        movieJpaRepository.findById(id).ifPresent(entity -> {
            entity.setTitle(movie.getTitle());
            entity.setSynopsis(movie.getSynopsis());
            entity.setReleaseYear(movie.getReleaseYear());
            movieJpaRepository.save(entity);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        movieJpaRepository.deleteById(id);
    }

}
