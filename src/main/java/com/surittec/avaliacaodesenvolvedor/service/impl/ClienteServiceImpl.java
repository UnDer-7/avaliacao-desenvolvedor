package com.surittec.avaliacaodesenvolvedor.service.impl;

import com.surittec.avaliacaodesenvolvedor.model.Cliente;
import com.surittec.avaliacaodesenvolvedor.repository.ClienteRepository;
import com.surittec.avaliacaodesenvolvedor.service.ClienteService;
import com.surittec.avaliacaodesenvolvedor.service.UtilsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
  private final UtilsService utilsService;
  private final ClienteRepository clienteRepository;

  @Override
  @Transactional()
  public Cliente create(Cliente cliente) {
    if (utilsService.isUserComum()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario nao tem permissao para criar cliente");
    }

    return clienteRepository.save(cliente);
  }
}
