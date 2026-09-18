package org.contrato.model.domain.exception;


import java.time.LocalDate;

public class FechaInvalidaExcepcion extends DomainException{

    protected static final String PLAZOS_INCONSISTENTE = "Los plazos presentan inconsistencias. Asegurese que el rango de tiempo sean validos";



    public FechaInvalidaExcepcion(String message) {
        super(message);
    }


    public static FechaInvalidaExcepcion fechaInconsistente(){
        return new FechaInvalidaExcepcion(String.format(PLAZOS_INCONSISTENTE));
    }


}
