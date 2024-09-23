package com.ceiba.biblioteca.usuarioServices;


import com.ceiba.biblioteca.usuarioServices.Usuario;
import com.ceiba.biblioteca.usuarioServices.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Api(value = "API de Usuarios", description = "Operaciones relacionadas con usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ApiOperation(value = "Crear un nuevo usuario", response = Usuario.class)
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar un usuario existente", response = Usuario.class)
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar un usuario")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un usuario por ID", response = Usuario.class)
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    @GetMapping
    @ApiOperation(value = "Listar todos los usuarios", response = List.class)
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}