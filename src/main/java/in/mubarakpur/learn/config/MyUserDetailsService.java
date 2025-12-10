
package in.mubarakpur.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.mubarakpur.learn.repository.UserRepository;

/**
 * UserDetailsService
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword()) // must be hashed (BCrypt encoded)
        .roles("ADMIN") // or user.getRole() later
        .build();
  }

  public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
    var user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword()) // Must be hashed (e.g., BCrypt encoded)
        .roles("ADMIN") // Or derive roles from user.getRole() or similar
        .build();
  }
}
