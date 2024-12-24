package br.uece.eescomdevops.filmesapi.controller;

import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
import br.uece.eescomdevops.filmesapi.service.FilmeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<FilmeReq> filmeReqJson;

    @MockitoBean
    private FilmeService filmeService;

    @Test
    @DisplayName("Deve cadastrar um novo filme")
    void filme_cenario1() throws Exception {
        FilmeReq filmeReq = new FilmeReq(
                "O Incrível Hulk",
                "Bruce Banner é um cientista que foge do governo dos Estados Unidos.",
                2008
        );

        when(filmeService.create(filmeReq)).thenReturn(1L);

        MockHttpServletResponse response = mockMvc.perform(
                    post("/v1/filmes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(filmeReqJson.write(filmeReq).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getHeader("location")).isEqualTo("http://localhost/v1/filmes/1");
    }

    @Test
    @DisplayName("Deve retornar erro 400 ao tentar cadastrar um novo filme sem um título")
    void filme_cenario2() throws Exception {
        FilmeReq filmeReq = new FilmeReq(
                "",
                "Bruce Banner é um cientista que foge do governo dos Estados Unidos.",
                2008
        );

        MockHttpServletResponse response = mockMvc.perform(
                    post("/v1/filmes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(filmeReqJson.write(filmeReq).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).contains("\"field\":\"titulo\",\"error\":\"must not be blank\"");
    }

}