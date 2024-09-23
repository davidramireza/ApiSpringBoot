
package com.ceiba.biblioteca.prestamoServices;

import com.ceiba.biblioteca.PrestamoNoPermitidoException;
import com.ceiba.biblioteca.usuarioServices.Usuario;
import com.ceiba.biblioteca.usuarioServices.UsuarioRepository;
import com.ceiba.biblioteca.usuarioServices.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoServices {
    @Autowired
    private PrestamoRepository prestamoRepository;

    private static final String PERFIL_INVITADO = "INVITADO";
    private static final String PERFIL_AFILIADO = "AFILIADO";
    private static final String PERFIL_EMPLEADO = "EMPLEADO";
    private static final String ESTADO_ACTIVO = "ACTIVO";


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public PrestamoServices(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    // Método que calcula la fecha de devolución sin contar sábados y domingos
    public LocalDate calcularFechaDevolucion(Usuario usuario) {
        int diasPrestamo;
        String perfil = String.valueOf(usuario.getPerfil());


        switch (perfil) {

            case PERFIL_AFILIADO:
                diasPrestamo = 10;
                break;
            case PERFIL_EMPLEADO:
                diasPrestamo = 8;
                break;
            case PERFIL_INVITADO:
                diasPrestamo = 7;
                break;
            default:
                throw new IllegalArgumentException("Perfil de usuario no válido");
        }

        LocalDate fechaDevolucion = LocalDate.now();
        int diasContados = 0;

        // Calcular la fecha sin contar sábados y domingos
        while (diasContados < diasPrestamo) {
            fechaDevolucion = fechaDevolucion.plusDays(1);
                if (!(fechaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY || fechaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                diasContados++;
            }
        }

        return fechaDevolucion;
    }

    public Prestamos crearPrestamo(Prestamos prestamo) {
        Usuario usuario = usuarioRepository.findById(prestamo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!puedeRealizarPrestamo(usuario)) {
            throw new PrestamoNoPermitidoException("El usuario no puede realizar préstamos");
        }

        // Calculamos la fecha de devolución
        LocalDate fechaDevolucion = calcularFechaDevolucion(usuario);
        prestamo.setFechaDevolucion(fechaDevolucion);

        prestamo.setEstado(ESTADO_ACTIVO);
        return prestamoRepository.save(prestamo);
    }

    public boolean puedeRealizarPrestamo(Usuario usuario) {
        // Implementación para verificar si el usuario puede realizar un préstamo
        if (PERFIL_INVITADO.equals(usuario.getPerfil())) {
            long prestamosActivos = prestamoRepository.contarPrestamosPorUsuarioYEstado(usuario.getId(), ESTADO_ACTIVO);
            if (prestamosActivos >= 1) {
                throw new RuntimeException("El usuario con identificación " + usuario.getIdentificacion() + " ya tiene un préstamo activo.");
            }
        }
        return true;
    }

    public Prestamos actualizarPrestamo(Long id, Prestamos  prestamoActualizado) {
        // Implementación
        return prestamoActualizado;
    }

    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }

    public Prestamos obtenerPrestamo(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    public List<Prestamos> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamos> listarPrestamosPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public List<Prestamos> listarPrestamosPorLibro(Long libroId) {
        return prestamoRepository.findByLibroId(libroId);
    }
}
