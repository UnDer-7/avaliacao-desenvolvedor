package com.surittec.avaliacaodesenvolvedor.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Mateus Cardoso
 */
@Data
@Entity
@Table(name = "clientes")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends BaseModelAbstract {

  @NotEmpty
  @Column(name = "nome", nullable = false)
  private String nome;

  @CPF
  @Column(name = "cpf", nullable = false)
  private String cpf;

  @NotEmpty
  @Column(name = "cep", nullable = false)
  private String cep;

  @NotEmpty
  @Column(name = "logradouro", nullable = false)
  private String logradouro;

  @NotEmpty
  @Column(name = "bairro", nullable = false)
  private String bairro;

  @NotEmpty
  @Column(name = "localidade", nullable = false)
  private String localidade;

  @NotEmpty
  @Column(name = "uf", nullable = false)
  private String uf;

  @Column(name = "unidade")
  private String unidade;

  @Column(name = "ibge")
  private String ibge;

  @Column(name = "complemento")
  private String complemento;

  @OneToMany(
    mappedBy = "cliente",
    cascade = CascadeType.PERSIST,
    orphanRemoval = true)
  private List<Telefone> telefones;

  @OneToMany(
    mappedBy = "cliente",
    cascade = CascadeType.PERSIST,
    orphanRemoval = true)
  private List<Email> emails;
}
