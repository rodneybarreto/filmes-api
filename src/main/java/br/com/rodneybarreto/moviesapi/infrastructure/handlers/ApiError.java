package br.com.rodneybarreto.moviesapi.infrastructure.handlers;

import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record ApiError(String field, String error) {

    public ApiError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

}
