package br.uece.eescomdevops.filmesapi.base.controller;

import br.uece.eescomdevops.filmesapi.base.dto.ErrorValidationRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationRes>> handleError400(MethodArgumentNotValidException ex) {
        List<ErrorValidationRes> errors =  ex.getFieldErrors().stream().map(ErrorValidationRes::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

}
