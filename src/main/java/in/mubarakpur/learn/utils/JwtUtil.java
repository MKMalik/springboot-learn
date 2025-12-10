package in.mubarakpur.learn.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import in.mubarakpur.learn.DTO.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import tools.jackson.databind.ObjectMapper;

@Component
public class JwtUtil {

  private final SecretKey key;

  public JwtUtil() {
    // MUST be 32+ chars
    String secret = "mysupersecretkeymysupersecretkey123";
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  private final long EXPIRATION = 86400_000; // 1 day
  private final ObjectMapper objectMapper = new ObjectMapper();

  public String generateToken(UserDTO dto) {
    String dtoJson = objectMapper.writeValueAsString(dto);
    return Jwts.builder()
        .subject(dtoJson)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(key)
        .compact();
  }

  public UserDTO extractUserDTO(String token) {
    String object = Jwts.parser()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    return objectMapper.readValue(object, UserDTO.class);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key)
          .build()
          .parseSignedClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
