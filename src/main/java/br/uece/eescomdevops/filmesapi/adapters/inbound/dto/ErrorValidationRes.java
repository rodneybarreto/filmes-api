package br.uece.eescomdevops.filmesapi.adapters.inbound.dto;

import org.springframework.validation.FieldError;

public record ErrorValidationRes(String field, String error) {

    public ErrorValidationRes(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

}
