package org.contrato.model.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.contrato.model.domain.valueobject.contrato.Estado;
import org.contrato.model.domain.valueobject.contrato.FrecuenciaPago;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "empresa", nullable = false)
    private String empresa;

    @Column(name = "empleado", nullable = false)
    private String empleado;

    @Column(name = "funciones", nullable = false, length = 1000)
    private String funciones;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "frecuenciapago", nullable = false)
    private FrecuenciaPago frecuenciaPago;

    @Column(name = "fechafin")
    private LocalDate fechaFin;

    @Column(name = "fechafirma")
    private LocalDate fechaFirma;

    @Column(name = "fechaincio")
    private LocalDate fechaInicio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    public void validarFechas() {
        if (fechaFin != null && fechaInicio != null &&
                fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException(
                    "La fecha fin no puede ser anterior al inicio");
        }
    }
}