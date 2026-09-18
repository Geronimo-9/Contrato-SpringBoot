package org.contrato.model.domain.exception;

import java.time.LocalDate;

public class ValoresNulosExcepcion extends DomainException {

    public static final String PARAMETRO_NULO = "El parametro '%s' esta nulo";
    
    
    public ValoresNulosExcepcion(String message) {
        super(message);
    }

    public static ValoresNulosExcepcion parametroNulo(String valorInvalido) {
        return new ValoresNulosExcepcion(String.format(PARAMETRO_NULO, valorInvalido));
    }

    public static ValoresNulosExcepcion fechaNula(LocalDate valorInvalido) {
        return new ValoresNulosExcepcion(String.format(PARAMETRO_NULO, valorInvalido));
    }
    
    
}
