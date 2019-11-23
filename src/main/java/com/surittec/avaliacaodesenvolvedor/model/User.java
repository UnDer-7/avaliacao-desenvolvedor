package com.surittec.avaliacaodesenvolvedor.model;

import com.surittec.avaliacaodesenvolvedor.model.enums.UserRoles;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author Mateus Cardoso
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModelAbstract {

  @NotEmpty
  @Column(name = "login", nullable = false)
  private String login;

  @NotEmpty
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRoles userRole;
}
