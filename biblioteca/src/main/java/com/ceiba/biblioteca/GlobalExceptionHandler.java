package com.ceiba.biblioteca;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PrestamoException.class)
    public ResponseEntity<Map<String, String>> handlePrestamoExcepcion(PrestamoException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PrestamoNoPermitidoException.class)
    public ResponseEntity<Map<String, String>> handlePrestamoNoPermitidoExcepcion(PrestamoNoPermitidoException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}