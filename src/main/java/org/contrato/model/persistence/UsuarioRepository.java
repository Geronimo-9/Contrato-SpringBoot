package org.contrato.model.persistence;

import org.contrato.model.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.contrasena = :contrasena")
    Optional<Usuario> findByIdAndContrasena(
            @Param("id") String id,
            @Param("contrasena") String contrasena
    );

    List<Usuario> findAllById(String id);

    List<Usuario> findByNombreContainingIgnoreCase(String nombre);


}