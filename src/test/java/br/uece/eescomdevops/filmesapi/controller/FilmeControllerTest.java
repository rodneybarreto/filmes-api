//package br.uece.eescomdevops.filmesapi.controller;
//
//import br.uece.eescomdevops.filmesapi.adapters.inbound.controller.FilmeController;
//import br.uece.eescomdevops.filmesapi.application.service.FilmeService;
//import br.uece.eescomdevops.filmesapi.domain.dto.FilmeReq;
//import br.uece.eescomdevops.filmesapi.domain.dto.FilmeRes;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//@WebMvcTest(FilmeController.class)
//class FilmeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private JacksonTester<FilmeReq> filmeReqJson;
//
//    @Autowired
//    private JacksonTester<FilmeRes> filmeRespJson;
//
//    @Autowired
//    private JacksonTester<Page<FilmeRes>> filmeRespPageJson;
//
//    @MockitoBean
//    private FilmeService filmeService;
//
//    @Test
//    @DisplayName("Deve cadastrar um novo filme")
//    void filme_cenario1() throws Exception {
//        FilmeReq filmeReq = new FilmeReq(
//                "O Incrível Hulk",
//                "Bruce Banner é um cientista que foge do governo dos Estados Unidos.",
//                2008
//        );
//
//        when(filmeService.create(filmeReq)).thenReturn(1L);
//
//        MockHttpServletResponse response = mockMvc.perform(
//                        post("/v1/filmes")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(filmeReqJson.write(filmeReq).getJson())
//                )
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//        assertThat(response.getHeader("location")).isEqualTo("http://localhost/v1/filmes/1");
//    }
//
//    @Test
//    @DisplayName("Deve retornar erro 400 ao tentar cadastrar um novo filme sem um título")
//    void filme_cenario2() throws Exception {
//        FilmeReq filmeReq = new FilmeReq(
//                "",
//                "Bruce Banner é um cientista que foge do governo dos Estados Unidos.",
//                2008
//        );
//
//        MockHttpServletResponse response = mockMvc.perform(
//                        post("/v1/filmes")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(filmeReqJson.write(filmeReq).getJson())
//                )
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//        assertThat(response.getContentAsString()).contains("\"field\":\"titulo\",\"error\":\"must not be blank\"");
//    }
//
//    @Test
//    @DisplayName("Deve retornar os filmes paginados")
//    void filme_cenario3() throws Exception {
//        List<FilmeRes> filmes = List.of(
//                new FilmeRes(1L, "O Incrível Hulk", "sinopse", 2008),
//                new FilmeRes(2L, "Capitão América", "sinopse", 2014)
//        );
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<FilmeRes> page = new PageImpl<>(filmes, pageable, filmes.size());
//
//        when(filmeService.findAll(any(Pageable.class))).thenReturn(page);
//
//        MockHttpServletResponse response = mockMvc.perform(get("/v1/filmes?page=0&size=10"))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        //TODO implementar assert equals correto
//        //assertThat(response.getContentAsString()).contains(filmeRespPageJson.write(page).getJson());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um filme pelo ID")
//    void filme_cenario4() throws Exception {
//        FilmeRes filmeResp = new FilmeRes(1L, "O Incrível Hulk", "sinopse", 2008);
//        when(filmeService.findById(1L)).thenReturn(filmeResp);
//
//        MockHttpServletResponse response = mockMvc.perform(get("/v1/filmes/1"))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(filmeRespJson.write(filmeResp).getJson());
//    }
//
//    @Test
//    @DisplayName("Deve retornar NOT FOUND quando não encontrar filme pelo ID")
//    void filme_cenario5() throws Exception {
//        doThrow(new EntityNotFoundException()).when(filmeService).findById(anyLong());
//
//        MockHttpServletResponse response = mockMvc.perform(get("/v1/filmes/1"))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar um filme")
//    void filme_cenario6() throws Exception {
//        FilmeReq filmeReq = new FilmeReq("Novo Título", "Nova Sinopse", 2023);
//
//        MockHttpServletResponse response = mockMvc.perform(put("/v1/filmes/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(filmeReqJson.write(filmeReq).getJson()))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
//    }
//
//    @Test
//    @DisplayName("Deve retornar NOT FOUND na atualização quando não encontrar o filme pelo id")
//    void filme_cenario7() throws Exception {
//        FilmeReq filmeReq = new FilmeReq("Novo Título", "Nova Sinopse", 2023);
//        doThrow(new EntityNotFoundException()).when(filmeService).update(anyLong(), any(FilmeReq.class));
//
//        MockHttpServletResponse response = mockMvc.perform(put("/v1/filmes/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(filmeReqJson.write(filmeReq).getJson()))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
//    }
//
//    @Test
//    @DisplayName("Deve deletar um filme pelo ID")
//    void filme_cenario8() throws Exception {
//        MockHttpServletResponse response = mockMvc.perform(delete("/v1/filmes/1"))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
//    }
//
//    @Test
//    @DisplayName("Deve retornar NOT FOUND quando o filme a ser deletado não for encontrado")
//    void filme_cenario9() throws Exception {
//        doThrow(new EntityNotFoundException()).when(filmeService).delete(anyLong());
//
//        MockHttpServletResponse response = mockMvc.perform(delete("/v1/filmes/1"))
//                .andDo(print())
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
//    }
//
//}
