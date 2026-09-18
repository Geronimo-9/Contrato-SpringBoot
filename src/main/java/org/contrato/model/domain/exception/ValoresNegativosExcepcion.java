package org.contrato.model.domain.exception;

import java.time.temporal.ValueRange;

public class ValoresNegativosExcepcion extends DomainException {

  public static final String VALORES_NEGATIVOS = "El valor no puede ser negativo";

  public ValoresNegativosExcepcion(String message) {
    super(message);
  }


  public static ValoresNegativosExcepcion valorNegativo(double valores){
      return new ValoresNegativosExcepcion(String.format(VALORES_NEGATIVOS, valores));
  }


}
