package com.surittec.avaliacaodesenvolvedor.security;

import com.surittec.avaliacaodesenvolvedor.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1704306946201419444L;

  @Getter
  private Integer id;

  private String login;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  UserDetailsImpl(User user) {
    this.id = Math.toIntExact(user.getId());
    this.login = user.getLogin();
    this.password = user.getPassword();

    final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().getDescricao());
    this.authorities = Collections.singleton(authority);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
