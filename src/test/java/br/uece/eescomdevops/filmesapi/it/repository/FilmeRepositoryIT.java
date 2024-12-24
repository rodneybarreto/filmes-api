package br.uece.eescomdevops.filmesapi.it.repository;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
import br.uece.eescomdevops.filmesapi.domain.entity.Filme;
import br.uece.eescomdevops.filmesapi.repository.FilmeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class FilmeRepositoryIT {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FilmeRepository filmeRepository;

    @BeforeEach
    void before() {
        FilmeReq filmeReq1 = new FilmeReq(
                "O Incrível Hulk",
                "Bruce Banner é um cientista que foge do governo dos Estados Unidos.",
                2008
        );
        FilmeReq filmeReq2 = new FilmeReq(
                "Capitão América: O Soldado Invernal",
                "Steve Roger se junta com a Viúva Negra para tentar deter uma nouva ameaça, um assasino conhecido como Soldado Invernal.",
                2014
        );
        testEntityManager.persistAndFlush(new Filme(filmeReq1));
        testEntityManager.persistAndFlush(new Filme(filmeReq2));
    }

    @Test
    @DisplayName("Deve listar os filmes cadastrados")
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "titulo"));
        Page<Filme> filmePage = filmeRepository.findAll(pageable);
        List<Filme> filmes = filmePage.getContent();

        Assertions.assertEquals(2, filmes.size());
        Assertions.assertEquals("Capitão América: O Soldado Invernal", filmes.get(0).getTitulo());
    }

}