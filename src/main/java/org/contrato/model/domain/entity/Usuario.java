package org.contrato.model.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.contrato.model.domain.valueobject.usuario.RolUsuario;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolUsuario rol;
}