package br.com.rodneybarreto.moviesapi.adapter.in.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.validation.FieldError;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ErrorResponse(String code, String error, String message, List<String> messages) {

    public ErrorResponse(FieldError fieldError) {
        this(null, fieldError.getField(), fieldError.getDefaultMessage(), null);
    }

}
