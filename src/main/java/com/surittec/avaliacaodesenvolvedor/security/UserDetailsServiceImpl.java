package com.surittec.avaliacaodesenvolvedor.security;

import com.surittec.avaliacaodesenvolvedor.model.User;
import com.surittec.avaliacaodesenvolvedor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Primary
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(final String login) {
    final User user = userRepository
      .findByLogin(login)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario " + login + " nao encontrado"));
    return new UserDetailsImpl(user);
  }
}
