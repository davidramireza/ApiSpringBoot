package com.ceiba.biblioteca.libroServices;

import com.ceiba.biblioteca.libroServices.Libro;
import com.ceiba.biblioteca.libroServices.LibroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Api(value = "API de Libros", description = "Operaciones relacionadas con usuarios")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @PostMapping
    @ApiOperation(value = "Crear un nuevo libro", response = Libro.class)
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar un libro existente", response = Libro.class)
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, libro));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un libro")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un libro por ID", response = Libro.class)
    public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerLibro(id));
    }

    @GetMapping
    @ApiOperation(value = "Listar todos los libros", response = List.class)
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }
}
