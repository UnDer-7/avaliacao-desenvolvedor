package com.surittec.avaliacaodesenvolvedor.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
public enum  UserRoles {
  ADMIN("ADMIN"),
  COMUM("COMUM");

  @Getter
  @Setter
  private String descricao;

  @JsonCreator
  public static UserRoles fromValue(final String value) {
    if (value.equalsIgnoreCase("admin")) {
      return UserRoles.ADMIN;
    }

    if (value.equalsIgnoreCase("comum")) {
      return UserRoles.COMUM;
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserRoles: " + value + " Nao encontrado");
  }
}
