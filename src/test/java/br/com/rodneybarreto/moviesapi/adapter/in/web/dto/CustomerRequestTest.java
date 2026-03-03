package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerRequestTest {

    private final Validator validator;

    public CustomerRequestTest() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

    @Test
    @DisplayName("O nome do cliente é obrigatório")
    void scenario_1() {
        var customerRequest = new CustomerRequest(null, "email@email.com", "0123456789");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The name is required");
    }

    @Test
    @DisplayName("O email do cliente é obrigatório")
    void scenario_2() {
        var customerRequest = new CustomerRequest("Jhon Doe", "", "0123456789");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The email is required");
    }

    @Test
    @DisplayName("A chave pix do cliente deve ter no máximo 255 caracteres")
    void scenario_3() {
        var pixKey = "a".repeat(256);
        var customerRequest = new CustomerRequest("Jhon Doe", "email@email", pixKey);

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The pix key must be less than 255 characters");
    }

    @Test
    @DisplayName("O email do cliente deve ser válido")
    void scenario_4() {
        var customerRequest = new CustomerRequest("Jhon Doe", "email", "01234567");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The email must be valid");
    }

    @Test
    @DisplayName("O email do cliente deve ter no máximo 255 caracteres")
    void scenario_5() {
        var email = "a".repeat(256);
        var customerRequest = new CustomerRequest("Jhon Doe", email, "01234567");

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);

        assertThat(violations).isNotEmpty();
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The email must be less than 255 characters")));
    }

}
