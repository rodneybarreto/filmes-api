package br.com.rodneybarreto.moviesapi.infrastructure.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

private static final String MALFORMED_REQUEST = "Malformed request";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleError400(MethodArgumentNotValidException ex) {
        List<ErrorDetails> errors =  ex.getFieldErrors().stream().map(ErrorDetails::new).toList();
        ErrorResponse errorResponse = ErrorResponse.builder().error(MALFORMED_REQUEST).details(errors).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
