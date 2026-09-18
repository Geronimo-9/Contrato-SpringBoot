package org.contrato.model.domain.valueobject.usuario;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;

import java.util.Objects;


public record IdUsuario(String valores) {


    private static final String CANTIDAD_DIGITO_CEDULA = "^\\d{10}$";
    private static final String CANTIDAD_NULA =  null;

    public IdUsuario{

        final String normalizarValor= Objects.requireNonNull(valores, "Debe ingresar tu documento de identidad").trim();
        validarSinEmpezar(normalizarValor);
        validarCantidadRequerida(normalizarValor);
        validarValorNulo(normalizarValor);
        valores = normalizarValor;

    }




    private static void validarSinEmpezar(final String normalizarValor){
        if (normalizarValor.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio(normalizarValor);
        }
    }


    private static void validarCantidadRequerida(final String valores){
            if (!valores.matches(CANTIDAD_DIGITO_CEDULA)){
                throw CantidadRequeridaExcepcion.cantidadRequerida(valores);
            }
    }

    private static void validarValorNulo(final String valores){
        if (valores.equals(CANTIDAD_NULA)) {
            throw ValoresNulosExcepcion.parametroNulo(valores);
        }
    }



}
