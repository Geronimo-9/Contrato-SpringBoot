package org.contrato.model.domain.exception;

public abstract class DomainException extends RuntimeException {

    protected DomainException(final String mensaje) {
        super(mensaje);
    }

    protected DomainException(final String mensaje, final Throwable causa) {
        super(mensaje, causa);
    }




}