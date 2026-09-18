package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;

import java.util.Objects;

public record Empresa(String valores) {

    private static final String SOLO_LETRAS = "^[a-zA-Z]+$";
    private static final String CANTIDAD_NULA =  null;

    public Empresa{
        validarCampoNulo(valores);
        final String normalizarValor= Objects.requireNonNull(valores, "Los nombres van sin caracteres especiales, ni numéricos").trim();
        validarSinEmpezar(normalizarValor);
        validarSoloLetras(normalizarValor);
        valores = normalizarValor;


    }

    private static void validarCampoNulo(String valores){
        if (valores == CANTIDAD_NULA) {
            throw ValoresNulosExcepcion.parametroNulo(valores);
        }
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
