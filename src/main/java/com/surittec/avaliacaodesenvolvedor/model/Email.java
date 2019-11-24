package com.surittec.avaliacaodesenvolvedor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author Mateus Cardoso
 */
@Data
@Entity
@Table(name = "emails")
@EqualsAndHashCode(callSuper = true)
public class Email extends BaseModelAbstract {

  @NotEmpty
  @Column(name = "email", nullable = false)
  @javax.validation.constraints.Email
  private String email;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;
}
