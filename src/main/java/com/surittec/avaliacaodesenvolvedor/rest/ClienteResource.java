package com.surittec.avaliacaodesenvolvedor.rest;

import com.surittec.avaliacaodesenvolvedor.model.Cliente;
import com.surittec.avaliacaodesenvolvedor.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Mateus Cardoso
 */
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteResource {
  private final Logger logger = LoggerFactory.getLogger(ClienteResource.class);

  private final ClienteService clienteService;

  @PostMapping
  public ResponseEntity<Cliente> createCliente(@Valid @RequestBody final Cliente cliente) {
    logger.debug("POST Request to create Cliente: {}", cliente);
    if (cliente.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um novo Cliente nao pode ter um Id");
    }

    final Cliente clienteSaved = clienteService.create(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteSaved);
  }

  @PutMapping
  public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody final Cliente cliente) {
    logger.debug("PUT Request to update Cliente: {}", cliente);
    if (cliente.getId() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente nao possui um Id");
    }

    final Cliente clienteSaved = clienteService.create(cliente);
    return ResponseEntity.status(HttpStatus.OK).body(clienteSaved);
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> getAllCliente() {
    logger.debug("GET Request to get all Clientes");

    final List<Cliente> clientes = clienteService.findAll();
    return ResponseEntity.ok(clientes);
  }

  @DeleteMapping("/{clienteId}")
  public ResponseEntity<Void> deleteCliente(@PathVariable long clienteId) {
    logger.debug("DELETE Request to delete an Cliente | ID: {}", clienteId);

    clienteService.delete(clienteId);
    return ResponseEntity.ok().build();
  }
}
