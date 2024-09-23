package com.ceiba.biblioteca.usuarioServices;
import com.ceiba.biblioteca.prestamoServices.Prestamos;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

   @Column(nullable = false)
    private String Identificacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPerfil perfil;



    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String password, TipoPerfil perfil,String Identificacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.perfil = perfil;
        this.Identificacion = Identificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(TipoPerfil perfil) {
        this.perfil = perfil;
    }


    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }
}
enum TipoPerfil {
    AFILIADO, EMPLEADO, INVITADO
}