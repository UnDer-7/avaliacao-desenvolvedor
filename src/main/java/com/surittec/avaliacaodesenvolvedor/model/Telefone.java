package com.surittec.avaliacaodesenvolvedor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.surittec.avaliacaodesenvolvedor.model.enums.TipoTelefone;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Mateus Cardoso
 */
@Data
@Entity
@Table(name = "telefones")
@EqualsAndHashCode(callSuper = true)
public class Telefone extends BaseModelAbstract {
  @Column(name = "numero", nullable = false)
  private String numero;

  @Column(name = "tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoTelefone tipoTelefone;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;
}
