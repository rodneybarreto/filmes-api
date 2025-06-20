package br.com.rodneybarreto.filmesapi.integrationtest.adapters.inbound.rest;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.ErrorValidationRes;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeReq;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeRes;
import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.PageRes;
import br.com.rodneybarreto.filmesapi.application.core.domain.entity.Filme;
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
class FilmeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<FilmeReq> filmeReqJson;

    @Autowired
    private JacksonTester<FilmeRes> filmeResJson;

    @Autowired
    private JacksonTester<List<ErrorValidationRes>> errorValidationResJson;

    @Autowired
    private JacksonTester<PageRes<Filme>> pageResJson;

    private FilmeReq filmeReq;

    @BeforeEach
    void setup() {
        filmeReq = new FilmeReq("A Identidade Bourne", "Um barco de pesca pega um homem amnésico", 2002);
    }

    @Test
    @DisplayName("Deve cadastrar um novo filme")
    void filme_cenario1() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/v1/filmes")
                        .contentType(APPLICATION_JSON)
                        .content(filmeReqJson.write(filmeReq).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getHeader("location")).isEqualTo("http://localhost/v1/filmes/2");
    }

    @Test
    @DisplayName("Deve retornar erro 400 ao tentar cadastrar um novo filme sem um título")
    void filme_cenario2() throws Exception {
        FilmeReq filmeBadReq = new FilmeReq("", "JUm barco de pesca pega um homem amnésico", 2002);

        MockHttpServletResponse response = mockMvc.perform(
                post("/v1/filmes")
                        .contentType(APPLICATION_JSON)
                        .content(filmeReqJson.write(filmeBadReq).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        ErrorValidationRes errorValidationRes = errorValidationResJson.parseObject(response.getContentAsString()).get(0);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(errorValidationRes.field()).isEqualTo("titulo");
        assertThat(errorValidationRes.error()).isEqualTo("must not be blank");
    }

    @Test
    @DisplayName("Deve retornar um filme pelo ID com sucesso")
    void filme_cenario3() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                    get("/v1/filmes/1")
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        FilmeRes filmeRes = filmeResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(filmeRes.id()).isEqualTo(1L);
        assertThat(filmeRes.titulo()).isEqualTo("O Incrível Hulk");
        assertThat(filmeRes.anoLancamento()).isEqualTo(2008);
    }

    @Test
    @DisplayName("Deve retornar NOT FOUND quando não encontrar filme pelo ID")
    void filme_cenario4() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/v1/filmes/3"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deve atualizar um filme")
    void filme_cenario5() throws Exception {
        FilmeReq filmeReqPut = new FilmeReq("Novo Título", "Nova Sinopse", 2023);

        MockHttpServletResponse response = mockMvc.perform(
                    put("/v1/filmes/1")
                            .contentType(APPLICATION_JSON)
                            .content(filmeReqJson.write(filmeReqPut).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve deletar um filme pelo ID")
    void filme_cenario6() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/v1/filmes/1"))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve retornar os filmes paginados")
    void filme_cenario7() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/v1/filmes?page=0&size=10"))
                .andDo(print())
                .andReturn()
                .getResponse();

        PageRes<Filme> page = pageResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(page.getPageNumber()).isZero();
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getTotalElements()).isEqualTo(2);
    }

}

