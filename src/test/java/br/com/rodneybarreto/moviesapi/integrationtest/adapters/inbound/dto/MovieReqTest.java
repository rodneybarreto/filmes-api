package br.com.rodneybarreto.moviesapi.integrationtest.adapters.inbound.dto;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.MovieReq;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MovieReqTest {

    private final Validator validator;
    private MovieReq movieReq;

    public MovieReqTest() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

    @Test
    @DisplayName("O título do filme é obrigatório")
    void scenario_1() {
        movieReq = new MovieReq(null, "Sinopse", 2025);

        Set<ConstraintViolation<MovieReq>> violations = validator.validate(movieReq);

        assertThat(violations).isNotEmpty();
    }

}
