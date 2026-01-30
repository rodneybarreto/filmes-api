package br.com.rodneybarreto.moviesapi.adapter.in.web.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdapter {

    public static final String MALFORMED_REQUEST = "Malformed request";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleError404(EntityNotFoundException ex) {
        var errorResponse = ErrorResponse.builder()
                .error(EntityNotFoundException.class.getSimpleName())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleError400(MethodArgumentNotValidException ex) {
        List<String> messages =  ex.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        var errorResponse = ErrorResponse.builder().error(MALFORMED_REQUEST).messages(messages).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
