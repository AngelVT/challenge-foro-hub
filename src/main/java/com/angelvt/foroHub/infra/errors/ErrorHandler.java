package com.angelvt.foroHub.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound(EntityNotFoundException e) {
        DatosErroValidacion error = new DatosErroValidacion("No encontrado", e.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handdle400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(DatosErroValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidacion(ValidationException e) {
        DatosErroValidacion error = new DatosErroValidacion("validacion", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity errorIntegridad(IntegrityValidation e) {
        DatosErroValidacion error = new DatosErroValidacion("integridad", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    private record DatosErroValidacion(String campo, String error) {
        public DatosErroValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
