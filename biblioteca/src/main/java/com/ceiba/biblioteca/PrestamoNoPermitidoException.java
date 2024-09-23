// Nueva excepción para cuando el préstamo no es permitido (por ejemplo, reglas adicionales)
package com.ceiba.biblioteca;

import com.ceiba.biblioteca.PrestamoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class PrestamoNoPermitidoException extends RuntimeException {
    public PrestamoNoPermitidoException(String message) {
        super(message);
    }
}

