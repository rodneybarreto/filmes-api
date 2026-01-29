package br.com.rodneybarreto.moviesapi.infrastructure.handler;

import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record ErrorDetails(String code, String message) {

    public ErrorDetails(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
