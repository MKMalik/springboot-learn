package in.mubarakpur.learn.controller;

import java.util.Map;

import javax.security.auth.login.FailedLoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.mubarakpur.learn.DTO.UserLoginDTO;
import in.mubarakpur.learn.mapper.UserMapper;
import in.mubarakpur.learn.repository.UserRepository;
import in.mubarakpur.learn.utils.JwtUtil;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserLoginDTO login) throws FailedLoginException {
    Authentication auth = authManager
        .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

    if (!auth.isAuthenticated())
      throw new FailedLoginException("username or password doesn't match");

    var user = userRepository.findByUsername(login.getUsername()).get();

    String token = jwtUtil.generateToken(UserMapper.toDTO(user));
    return ResponseEntity.ok(Map.of("token", token));
  }

}
