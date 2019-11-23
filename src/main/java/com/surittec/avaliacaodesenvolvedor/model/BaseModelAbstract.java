package com.surittec.avaliacaodesenvolvedor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mateus Cardoso
 */
@MappedSuperclass
abstract class BaseModelAbstract implements Serializable {
  private static final long serialVersionUID = 137167500636116327L;

  @Id
  @Getter
  @Setter
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @Setter
  @Column(name = "created_at")
  private LocalDate createdAt;
}
