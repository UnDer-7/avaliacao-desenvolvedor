package com.surittec.avaliacaodesenvolvedor.service.impl;

import com.surittec.avaliacaodesenvolvedor.model.Cliente;
import com.surittec.avaliacaodesenvolvedor.repository.ClienteRepository;
import com.surittec.avaliacaodesenvolvedor.service.ClienteService;
import com.surittec.avaliacaodesenvolvedor.service.UtilsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Mateus Cardoso
 */
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
  private final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

  private final UtilsService utilsService;
  private final ClienteRepository clienteRepository;

  @Override
  @Transactional
  public Cliente create(Cliente cliente) {
    canProceed();
    if (cliente.getId() == null) {
      logger.info("Creating new Cliente: {}", cliente);
    } else {
      logger.info("Updating an Cliente: {}", cliente);
    }


    setCliente(cliente);
    return clienteRepository.save(cliente);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Cliente> findAll() {
    logger.info("Getting all Clientes");
    return clienteRepository.findAll();
  }

  @Override
  public Cliente findOne(final long clienteId) {
    logger.info("Getting one Cliente");

    return clienteRepository.findById(clienteId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario nao encontrado"));
  }

  @Override
  @Transactional
  public void delete(final long clienteId) {
    canProceed();
    logger.info("Deleting an Cliente | ID: {}", clienteId);
    clienteRepository.deleteById(clienteId);
  }

  /**
   * <h1>canProceed</h1>
   * <h3>
   *   Verifica se usuaria logado tem a permissao para
   *   executar a acao
   * </h3>
   * @throws ResponseStatusException Se o usuario logado for do tipo COMUM
   */
  private void canProceed() {
    if (utilsService.isUserComum()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario nao tem permissao para criar cliente");
    }
  }

  /**
   * <h1>setCliente</h1>
   * <h3>
   *   Seta o {@link Cliente} nas entidades {@link com.surittec.avaliacaodesenvolvedor.model.Email}
   *   e {@link com.surittec.avaliacaodesenvolvedor.model.Telefone} para o CascadeType.PERSIST do
   *   relacionamento funcionar.
   * </h3>
   * @param cliente Cliente para ser setado o relacionamento
   */
  private void setCliente(final Cliente cliente) {
    cliente.getEmails().forEach(item -> item.setCliente(cliente));
    cliente.getTelefones().forEach(item -> item.setCliente(cliente));
  }
}
