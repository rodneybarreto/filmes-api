package br.com.rodneybarreto.filmesapi.infrastructure.handlers;

import org.springframework.validation.FieldError;

public record ApiError(String field, String error) {

    public ApiError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

}
