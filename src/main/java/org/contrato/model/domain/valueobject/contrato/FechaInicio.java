package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record FechaInicio(LocalDate fecha) {

    private static final LocalDate FECHA_NULA = null;



    public FechaInicio {
        validarFechaNula(fecha);
    }


    private static void validarFechaNula(final LocalDate fecha){
        if (fecha == FECHA_NULA) {
            throw ValoresNulosExcepcion.fechaNula(fecha);
        }
    }


}
