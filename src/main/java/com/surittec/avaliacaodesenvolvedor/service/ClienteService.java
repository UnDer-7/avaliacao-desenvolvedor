package com.surittec.avaliacaodesenvolvedor.service;

import com.surittec.avaliacaodesenvolvedor.model.Cliente;

import java.util.List;

public interface ClienteService {
  Cliente create(final Cliente cliente);

  List<Cliente> findAll();

  Cliente findOne(final long ClienteId);

  void delete(final long clienteId);
}
