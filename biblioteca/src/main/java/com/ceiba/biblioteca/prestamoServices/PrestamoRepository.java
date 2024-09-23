package com.ceiba.biblioteca.prestamoServices;

import com.ceiba.biblioteca.prestamoServices.Prestamos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamos, Long> {
    List<Prestamos> findByUsuarioId(Long usuarioId);
    List<Prestamos> findByLibroId(Long libroId);
    @Query("SELECT COUNT(p) FROM Prestamos p WHERE p.usuario.id = :usuarioId AND p.Estado = :estado")
    long contarPrestamosPorUsuarioYEstado(@Param("usuarioId") Long usuarioId, @Param("estado") String estado);
}
