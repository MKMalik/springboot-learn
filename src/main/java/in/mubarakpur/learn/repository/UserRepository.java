package in.mubarakpur.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mubarakpur.learn.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUsername(String username);
}
