package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MovieRequestTest {

    private final Validator validator;

    public MovieRequestTest() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

    @Test
    @DisplayName("O título do filme é obrigatório")
    void scenario_1() {
        MovieRequest movieRequest = new MovieRequest(null, "Synopsis", 2025);

        Set<ConstraintViolation<MovieRequest>> violations = validator.validate(movieRequest);

        assertThat(violations).isNotEmpty();
    }

}
