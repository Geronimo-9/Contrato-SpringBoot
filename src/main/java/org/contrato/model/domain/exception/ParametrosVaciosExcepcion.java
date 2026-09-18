package org.contrato.model.domain.exception;

import java.time.LocalDate;

public class ParametrosVaciosExcepcion extends DomainException {

    public static final String PARAMETRO_VACIO = "El parametro '%s' No puede quedar vacio";


    public  ParametrosVaciosExcepcion(final String message) {
        super(message);
    }


    public static ParametrosVaciosExcepcion parametroVacio(String valorInvalido) {
        return new ParametrosVaciosExcepcion(String.format(PARAMETRO_VACIO, valorInvalido));
    }

    public static ParametrosVaciosExcepcion fechaVacia(LocalDate valorInvalido) {
        return new ParametrosVaciosExcepcion(String.format(PARAMETRO_VACIO, valorInvalido));
    }







}
