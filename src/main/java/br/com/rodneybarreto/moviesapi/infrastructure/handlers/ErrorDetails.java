package br.com.rodneybarreto.moviesapi.infrastructure.handlers;

import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record ErrorDetails(String code, String error) {

    public ErrorDetails(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
