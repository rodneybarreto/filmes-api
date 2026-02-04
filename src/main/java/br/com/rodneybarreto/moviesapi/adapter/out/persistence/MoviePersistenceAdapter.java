package br.com.rodneybarreto.moviesapi.adapter.out.persistence;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
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
    public Movie findById(long id) {
        return movieJpaRepository.findById(id)
                .map(MovieJpaEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    @Override
    public PageResponse<Movie> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy, String searchTerm) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));

        Page<MovieJpaEntity> page = (searchTerm == null || searchTerm.isBlank())
                ? movieJpaRepository.findAll(pageable)
                : movieJpaRepository.findByTitleContainsIgnoreCase(searchTerm, pageable);

        return new PageResponse<>(
                MovieJpaEntity.toDomain(page.getContent()),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    @Transactional
    public void update(long id, Movie movie) {
        movieJpaRepository.findById(id).ifPresent(entity -> {
            entity.setTitle(movie.getTitle());
            entity.setSynopsis(movie.getSynopsis());
            entity.setReleaseYear(movie.getReleaseYear());
            movieJpaRepository.save(entity);
        });
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        movieJpaRepository.deleteById(id);
    }

}
