package org.contrato.model.domain.valueobject.usuario;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;

public record ContrasenaUsuario(String valores) {

    private static final int MAXIMA_LONGITUD = 10;
    private static final int MINIMA_LONGITUD = 5;
    private static final String CONTRASEÑA_NULA =  null;
    private static final String CONTRASEÑA_VACIA =  "";

    public ContrasenaUsuario{

    validarCampoNulo(valores);
    validarCampoVacio(valores);
    validarTamañoParametro(valores);


    }


    private static void validarTamañoParametro(String valores){
        if (valores.length() < MINIMA_LONGITUD || MAXIMA_LONGITUD < valores.length()) {
            throw CantidadRequeridaExcepcion.fueraDeRango(valores);
        }
    }

    private static void validarCampoNulo(String valores){
        if (valores == CONTRASEÑA_NULA) {
            throw ValoresNulosExcepcion.parametroNulo(valores);
        }
    }

    private static void validarCampoVacio(String valores){
        if (valores.equals(CONTRASEÑA_VACIA)) {
            throw ParametrosVaciosExcepcion.parametroVacio(valores);
        }
    }


}
