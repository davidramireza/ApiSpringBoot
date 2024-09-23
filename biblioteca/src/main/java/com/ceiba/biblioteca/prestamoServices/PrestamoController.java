package com.ceiba.biblioteca.prestamoServices;

import com.ceiba.biblioteca.PrestamoException;
import com.ceiba.biblioteca.prestamoServices.Prestamos;
import com.ceiba.biblioteca.prestamoServices.PrestamoServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/prestamos")
@Api(value = "API de Usuarios", description = "Operaciones relacionadas con usuarios")
public class PrestamoController {
    @Autowired

   // private Prestamos prestamos;
    private PrestamoServices prestamoServices;



    @PostMapping
    @ApiOperation(value = "Crear un nuevo préstamo", response = Prestamos.class)
    public ResponseEntity<?> crearPrestamo(@RequestBody Prestamos prestamo) {
        try {
            Prestamos nuevoPrestamo =  prestamoServices.crearPrestamo(prestamo);
            return ResponseEntity.ok(nuevoPrestamo);
        } catch (PrestamoException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("mensaje", ex.getMessage()));
        }
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar un prestamos existente", response = Prestamos.class)
    public ResponseEntity<Prestamos> actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamos prestamo) {
        return ResponseEntity.ok(prestamoServices.actualizarPrestamo(id, prestamo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un prestamos")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        prestamoServices.eliminarPrestamo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un prestamos por ID", response = Prestamos.class)
    public ResponseEntity<Prestamos> obtenerPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoServices.obtenerPrestamo(id));
    }

    @GetMapping
    @ApiOperation(value = "Listar todos los prestamos", response = List.class)
    public ResponseEntity<List<Prestamos>> listarPrestamos() {
        return ResponseEntity.ok(prestamoServices.listarPrestamos());
    }

    @GetMapping("/usuario/{usuarioId}")
    @ApiOperation(value = "Obtener un prestamos por ID", response = Prestamos.class)
    public ResponseEntity<List<Prestamos>> listarPrestamosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(prestamoServices.listarPrestamosPorUsuario(usuarioId));
    }

}

