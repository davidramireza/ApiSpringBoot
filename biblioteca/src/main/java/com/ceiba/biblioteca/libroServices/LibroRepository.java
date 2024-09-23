package com.ceiba.biblioteca.libroServices;

import com.ceiba.biblioteca.libroServices.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Libro findByIsbn(String isbn);
}