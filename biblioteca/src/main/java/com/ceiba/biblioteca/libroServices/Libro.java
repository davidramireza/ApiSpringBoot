package com.ceiba.biblioteca.libroServices;

import com.ceiba.biblioteca.prestamoServices.Prestamos;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String isbn;



    // Getters, setters, constructores


    public Libro() {
    }

    public Libro(Long id, String titulo, String autor, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
