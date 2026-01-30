package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.MovieResponse;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.PageResponse;
import br.com.rodneybarreto.moviesapi.adapter.in.web.exception.ErrorResponse;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.rodneybarreto.moviesapi.adapter.in.web.MovieControllerAdapter.MOVIE_RESOURCE;
import static br.com.rodneybarreto.moviesapi.adapter.in.web.exception.ExceptionControllerAdapter.MALFORMED_REQUEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@Sql(scripts = "/sql/movie/before.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/movie/after.sql", executionPhase = AFTER_TEST_METHOD)
class MovieControllerAdapterIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<MovieRequest> movieReqJson;

    @Autowired
    private JacksonTester<MovieResponse> movieResJson;

    @Autowired
    private JacksonTester<ErrorResponse> errorValidationResJson;

    @Autowired
    private JacksonTester<PageResponse<Movie>> pageResJson;

    private MovieRequest movieRequest;

    @BeforeEach
    void setup() {
        movieRequest = new MovieRequest("A Identidade Bourne", "Um barco de pesca pega um homem amnésico", 2002);
    }

    @Test
    @DisplayName("Deve cadastrar um novo filme com sucesso")
    void movie_cenario1() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post(MOVIE_RESOURCE)
                        .contentType(APPLICATION_JSON)
                        .content(movieReqJson.write(movieRequest).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getHeader("location")).isEqualTo("http://localhost/v1/movies/2");
    }

    @Test
    @DisplayName("Deve retornar BAD REQUEST ao tentar cadastrar um novo filme sem um título")
    void movie_cenario2() throws Exception {
        MovieRequest movieBadReq = new MovieRequest("", "Um barco de pesca pega um homem amnésico", 2002);

        MockHttpServletResponse response = mockMvc.perform(
                post(MOVIE_RESOURCE)
                        .contentType(APPLICATION_JSON)
                        .content(movieReqJson.write(movieBadReq).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        ErrorResponse errorResponse = errorValidationResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(errorResponse.error()).isEqualTo(MALFORMED_REQUEST);
        assertThat(errorResponse.messages().getFirst()).isEqualTo("The title is required");
    }

    @Test
    @DisplayName("Deve retornar um filme pelo ID com sucesso")
    void movie_cenario3() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                    get(MOVIE_RESOURCE + "/1")
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        MovieResponse movieResponse = movieResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(movieResponse.id()).isEqualTo(1L);
        assertThat(movieResponse.title()).isEqualTo("O Incrível Hulk");
        assertThat(movieResponse.releaseYear()).isEqualTo(2008);
    }

    @Test
    @DisplayName("Deve retornar NOT FOUND quando não encontrar o filme pelo ID")
    void movie_cenario4() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(MOVIE_RESOURCE + "/3"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deve atualizar um filme com sucesso")
    void movie_cenario5() throws Exception {
        MovieRequest movieRequestPut = new MovieRequest("Novo Título", "Nova Synopsis", 2023);

        MockHttpServletResponse response = mockMvc.perform(
                    put(MOVIE_RESOURCE + "/1")
                            .contentType(APPLICATION_JSON)
                            .content(movieReqJson.write(movieRequestPut).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve deletar um filme pelo ID com sucesso")
    void movie_cenario6() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete(MOVIE_RESOURCE + "/1"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve retornar os filmes paginados")
    void movie_cenario7() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(MOVIE_RESOURCE + "?page=0&size=10"))
                .andDo(print())
                .andReturn()
                .getResponse();

        PageResponse<Movie> page = pageResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(page.getPageNumber()).isZero();
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getTotalElements()).isEqualTo(2);
    }

}

