package com.surittec.avaliacaodesenvolvedor.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum TipoTelefone {
  RESIDENCIAL,
  COMERCIAL,
  CELULAR;

  @JsonCreator
  public static TipoTelefone fromValue(final String value) {
    if (value.equalsIgnoreCase("residencial")) {
      return TipoTelefone.RESIDENCIAL;
    }

    if (value.equalsIgnoreCase("comercial")) {
      return TipoTelefone.COMERCIAL;
    }

    if (value.equalsIgnoreCase("celular")) {
      return TipoTelefone.CELULAR;
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TipoTelefone: " + value + " Nao encontrado");
  }
}
