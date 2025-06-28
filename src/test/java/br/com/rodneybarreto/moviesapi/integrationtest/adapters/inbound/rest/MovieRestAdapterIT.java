package br.com.rodneybarreto.moviesapi.integrationtest.adapters.inbound.rest;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieReq;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieRes;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.moviesapi.application.core.domain.Movie;
import br.com.rodneybarreto.moviesapi.infrastructure.handlers.ApiError;
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

import java.util.List;

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
@Sql(scripts = "/sql/before.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/after.sql", executionPhase = AFTER_TEST_METHOD)
class MovieRestAdapterIT {

    public static final String MOVIES = "/v1/movies";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<MovieReq> movieReqJson;

    @Autowired
    private JacksonTester<MovieRes> movieResJson;

    @Autowired
    private JacksonTester<List<ApiError>> errorValidationResJson;

    @Autowired
    private JacksonTester<PageRes<Movie>> pageResJson;

    private MovieReq movieReq;

    @BeforeEach
    void setup() {
        movieReq = new MovieReq("A Identidade Bourne", "Um barco de pesca pega um homem amnésico", 2002);
    }

    @Test
    @DisplayName("Deve cadastrar um novo movie")
    void movie_cenario1() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post(MOVIES)
                        .contentType(APPLICATION_JSON)
                        .content(movieReqJson.write(movieReq).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getHeader("location")).isEqualTo("http://localhost/v1/movies/2");
    }

    @Test
    @DisplayName("Deve retornar erro 400 ao tentar cadastrar um novo movie sem um título")
    void movie_cenario2() throws Exception {
        MovieReq movieBadReq = new MovieReq("", "JUm barco de pesca pega um homem amnésico", 2002);

        MockHttpServletResponse response = mockMvc.perform(
                post(MOVIES)
                        .contentType(APPLICATION_JSON)
                        .content(movieReqJson.write(movieBadReq).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        ApiError apiError = errorValidationResJson.parseObject(response.getContentAsString()).get(0);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(apiError.field()).isEqualTo("titulo");
        assertThat(apiError.error()).isEqualTo("must not be blank");
    }

    @Test
    @DisplayName("Deve retornar um movie pelo ID com sucesso")
    void movie_cenario3() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                    get(MOVIES + "/1")
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        MovieRes movieRes = movieResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(movieRes.id()).isEqualTo(1L);
        assertThat(movieRes.titulo()).isEqualTo("O Incrível Hulk");
        assertThat(movieRes.anoLancamento()).isEqualTo(2008);
    }

    @Test
    @DisplayName("Deve retornar NOT FOUND quando não encontrar movie pelo ID")
    void movie_cenario4() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(MOVIES + "/3"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deve atualizar um movie")
    void movie_cenario5() throws Exception {
        MovieReq movieReqPut = new MovieReq("Novo Título", "Nova Sinopse", 2023);

        MockHttpServletResponse response = mockMvc.perform(
                    put(MOVIES + "/1")
                            .contentType(APPLICATION_JSON)
                            .content(movieReqJson.write(movieReqPut).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve deletar um movie pelo ID")
    void movie_cenario6() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete(MOVIES + "/1"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve retornar os movies paginados")
    void movie_cenario7() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(MOVIES + "?page=0&size=10"))
                .andDo(print())
                .andReturn()
                .getResponse();

        PageRes<Movie> page = pageResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(page.getPageNumber()).isZero();
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getTotalElements()).isEqualTo(2);
    }

}

