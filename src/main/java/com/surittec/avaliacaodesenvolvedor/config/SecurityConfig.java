package com.surittec.avaliacaodesenvolvedor.config;

import com.surittec.avaliacaodesenvolvedor.security.JWTAuthenticationFilter;
import com.surittec.avaliacaodesenvolvedor.security.JWTAuthorizationFilter;
import com.surittec.avaliacaodesenvolvedor.security.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private static final String[] PUBLIC_ENDPOINT = {
    "/h2-console/**"
  };

  private final UserDetailsService userDetailsService;
  private final JWTUtils jwtUtils;

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .cors()
      .and()
      .headers()
      .frameOptions()
      .disable()
      .and()
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers(PUBLIC_ENDPOINT)
      .permitAll()
      .anyRequest()
      .authenticated();
    httpSecurity.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtils));
    httpSecurity.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtils, userDetailsService));
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
