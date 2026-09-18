package org.contrato.model.domain.exception;

public class FormatoInvalidoExcepcion extends DomainException{

    private static final String FORMATO_INCORRECTO= "El identificador '%s' contiene caracteres inválidos.";

    protected FormatoInvalidoExcepcion(final String message) {
        super(message);
    }


    public static FormatoInvalidoExcepcion formatoIncorrecto(final String invalidValue) {
        return new FormatoInvalidoExcepcion(String.format(FORMATO_INCORRECTO, invalidValue));
    }
}
