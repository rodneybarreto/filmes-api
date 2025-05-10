package br.uece.eescomdevops.filmesapi.adapters.inbound.rest;

import br.uece.eescomdevops.filmesapi.adapters.inbound.dto.ErrorValidationRes;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationRes>> handleError400(MethodArgumentNotValidException ex) {
        List<ErrorValidationRes> errors =  ex.getFieldErrors().stream().map(ErrorValidationRes::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

}
