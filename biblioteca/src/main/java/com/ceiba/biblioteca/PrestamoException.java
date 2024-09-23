package com.ceiba.biblioteca;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// Excepción personalizada para préstamos cuando ya existe uno activo
public class PrestamoException extends RuntimeException {

    private final String identificacionUsuario;

    public PrestamoException(String identificacionUsuario) {
        super("El usuario con identificación " + identificacionUsuario + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }
}

