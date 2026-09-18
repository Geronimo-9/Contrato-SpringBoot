package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;

import java.time.LocalDate;

public record FechaFirma(LocalDate fecha) {

    private static final LocalDate FECHA_NULA = null;



    public FechaFirma {
        validarFechaNula(fecha);
    }


    private static void validarFechaNula(final LocalDate fecha){
        if (fecha == FECHA_NULA) {
            throw ValoresNulosExcepcion.fechaNula(fecha);
        }
    }


}
