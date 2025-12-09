package in.mubarakpur.learn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import in.mubarakpur.learn.model.User;
import in.mubarakpur.learn.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User getUser(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  public User addUser(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "username taken");
    }
    return userRepository.save(user);
  }

  public void removeUser(long id) {
    if (!userRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    userRepository.deleteById(id);
  }

  public User updateUser(long id, User updatedUser) {
    return userRepository.findById(id)
        .map(user -> {
          user.setUsername(updatedUser.getUsername());
          user.setPassword(updatedUser.getPassword());
          return userRepository.save(user);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }
}
