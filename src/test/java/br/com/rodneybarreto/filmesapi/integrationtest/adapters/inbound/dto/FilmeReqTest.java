package br.com.rodneybarreto.filmesapi.integrationtest.adapters.inbound.dto;

import br.com.rodneybarreto.filmesapi.adapters.inbound.dto.FilmeReq;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class FilmeReqTest {

    private final Validator validator;
    private FilmeReq filmeReq;

    public FilmeReqTest() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

    @Test
    @DisplayName("O título do filme é obrigatório")
    void scenario_1() {
        filmeReq = new FilmeReq(null, "Sinopse", 2025);

        Set<ConstraintViolation<FilmeReq>> violations = validator.validate(filmeReq);

        assertThat(violations).isNotEmpty();
    }

}
