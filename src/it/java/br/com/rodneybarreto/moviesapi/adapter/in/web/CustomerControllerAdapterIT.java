package br.com.rodneybarreto.moviesapi.adapter.in.web;

import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerRequest;
import br.com.rodneybarreto.moviesapi.adapter.in.web.dto.CustomerResponse;
import br.com.rodneybarreto.moviesapi.adapter.in.web.exception.ErrorResponse;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity.CustomerJpaEntity;
import br.com.rodneybarreto.moviesapi.adapter.out.persistence.repository.CustomerJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.rodneybarreto.moviesapi.adapter.in.web.CustomerControllerAdapter.CUSTOMER_RESOURCE;
import static br.com.rodneybarreto.moviesapi.adapter.in.web.exception.ExceptionControllerAdapter.MALFORMED_REQUEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class CustomerControllerAdapterIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<CustomerRequest> customerReqJson;

    @Autowired
    private JacksonTester<CustomerResponse> customerResJson;

    @Autowired
    private JacksonTester<ErrorResponse> errorValidationResJson;

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    private CustomerJpaEntity customer;

    @BeforeEach
    void before() {
        var entity = CustomerJpaEntity.builder().name("Alfred").email("alfred@email.com").pixKey("9876543210").build();
        customer = customerJpaRepository.save(entity);
    }

    @AfterEach
    void after() {
        customerJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve cadastrar um novo cliente com sucesso")
    void customer_scenario1() throws Exception {
        var customerRequest = new CustomerRequest("Jhon", "jhon@email.com", "0123456789");

        MockHttpServletResponse response = mockMvc.perform(
                    post(CUSTOMER_RESOURCE)
                        .contentType(APPLICATION_JSON)
                        .content(customerReqJson.write(customerRequest).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getHeader("location")).contains(CUSTOMER_RESOURCE + "/");
    }

    @Test
    @DisplayName("Deve retornar BAD REQUEST ao tentar cadastrar um novo cliente sem um nome")
    void customer_scenario2() throws Exception {
        var customerBadReq = new CustomerRequest("", "jhon@email.com", "0123456789");

        MockHttpServletResponse response = mockMvc.perform(
                post(CUSTOMER_RESOURCE)
                        .contentType(APPLICATION_JSON)
                        .content(customerReqJson.write(customerBadReq).getJson())
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        ErrorResponse errorResponse = errorValidationResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(errorResponse.error()).isEqualTo(MALFORMED_REQUEST);
        assertThat(errorResponse.messages().getFirst()).isEqualTo("The name is required");
    }

    @Test
    @DisplayName("Deve retornar um cliente pelo ID com sucesso")
    void customer_scenario3() throws Exception {
        var customerId = customer.getId();
        MockHttpServletResponse response = mockMvc.perform(
                    get(CUSTOMER_RESOURCE + "/" + customerId)
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        CustomerResponse customerResponse = customerResJson.parseObject(response.getContentAsString());

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(customerResponse.id()).isEqualTo(customerId);
        assertThat(customerResponse.name()).isEqualTo("Alfred");
        assertThat(customerResponse.email()).isEqualTo("alfred@email.com");
        assertThat(customerResponse.pixKey()).isEqualTo("9876543210");
    }

    @Test
    @DisplayName("Deve retornar NOT FOUND quando não encontrar o cliente pelo ID")
    void customer_scenario4() throws Exception {
        var customerId = customer.getId();
        MockHttpServletResponse response = mockMvc.perform(get(CUSTOMER_RESOURCE + "/" + (customerId + 1)))
                .andDo(print())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
