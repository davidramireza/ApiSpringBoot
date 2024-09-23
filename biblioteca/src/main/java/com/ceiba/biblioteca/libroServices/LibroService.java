package com.ceiba.biblioteca.libroServices;

import com.ceiba.biblioteca.libroServices.Libro;
import com.ceiba.biblioteca.libroServices.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        // Implementación
        return libroActualizado;
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public Libro obtenerLibro(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }
}