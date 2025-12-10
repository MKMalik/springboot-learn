package in.mubarakpur.learn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mubarakpur.learn.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

  Optional<User> findById(Long id);
}
