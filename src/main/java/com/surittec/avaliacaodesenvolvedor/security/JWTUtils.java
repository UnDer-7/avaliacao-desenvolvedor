package com.surittec.avaliacaodesenvolvedor.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  String generateToken(final String username) {
    return Jwts.builder()
      .setSubject(username)
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(SignatureAlgorithm.HS512, secret.getBytes())
      .compact();
  }

  boolean isTokenValid(final String token) {
    Claims claims = getClaims(token);
    if (claims != null) {
      final String user = claims.getSubject();
      final Date expirationDate = claims.getExpiration();
      final Date now = new Date(System.currentTimeMillis());
      return user != null && now.before(expirationDate);
    }
    return false;
  }

  String getUsername(final String token) {
    final Claims claims = getClaims(token);
    if (claims != null) {
      return claims.getSubject();
    }
    return null;
  }

  private Claims getClaims(final String token) {
      return Jwts
        .parser()
        .setSigningKey(secret.getBytes())
        .parseClaimsJws(token)
        .getBody();
  }
}
