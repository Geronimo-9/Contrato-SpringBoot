package org.contrato.services;

import org.contrato.model.domain.entity.Usuario;
import org.contrato.model.domain.exception.DomainException;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.usuario.ContrasenaUsuario;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;
import org.contrato.model.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void guardarUsuario(Usuario usuario) throws Exception {

        if (usuario.getId() == null || usuario.getId().isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        usuarioRepository.save(usuario);
    }

    public void modificarUsuario(Usuario usuario) throws Exception {

        if (usuario.getId() == null || usuario.getId().isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new Exception("Usuario no encontrado."));

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setContrasena(usuario.getContrasena());
        usuarioExistente.setRol(usuario.getRol());

        usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(String id) throws Exception {

        if (id == null || id.isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        usuarioRepository.deleteById(id);
    }

    public Usuario iniciarSesion(String id, String contrasena) throws Exception {

        IdUsuario idUsuario = new IdUsuario(id);
        ContrasenaUsuario contrasenaUsuario = new ContrasenaUsuario(contrasena);


        return usuarioRepository.findByIdAndContrasena(
                idUsuario.valores(),
                contrasenaUsuario.valores()
        ).orElseThrow(() ->
                new Exception("Credenciales incorrectas o usuario no encontrado.")
        );
    }

    public Usuario consultarUsuario(String id) throws Exception {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Usuario no encontrado."));
    }

    public List<Usuario> listarUsuarios() throws Exception {

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new Exception("No hay usuarios registrados.");
        }

        return usuarios;
    }

    public List<Usuario> filtrarUsuarios(String id, String nombre) {
        if (id != null && !id.trim().isEmpty()) {
            return usuarioRepository.findAllById(id.trim());
        }

        if (nombre != null && !nombre.trim().isEmpty()) {
            return usuarioRepository.findByNombreContainingIgnoreCase(nombre.trim());
        }

        return usuarioRepository.findAll();
    }
}