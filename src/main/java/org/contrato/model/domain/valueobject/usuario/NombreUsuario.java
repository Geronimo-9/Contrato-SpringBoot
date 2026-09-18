package org.contrato.model.domain.valueobject.usuario;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;

import java.util.Objects;

public record NombreUsuario(String valores) {

    private static final String SOLO_LETRAS = "^[a-zA-Z]+$";


    public NombreUsuario{

        final String normalizarValor= Objects.requireNonNull(valores, "Los nombres van sin caracteres especiales, ni numéricos").trim();
        validarSinEmpezar(normalizarValor);
        validarSoloLetras(normalizarValor);
        valores = normalizarValor;

    }





    private static void validarSinEmpezar(final String normalizarValor){
        if (normalizarValor.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio(normalizarValor);
        }
    }

    private static void validarSoloLetras(final String valores){
        if (!valores.matches(SOLO_LETRAS)){
            throw FormatoInvalidoExcepcion.formatoIncorrecto(valores);
        }
    }



}
