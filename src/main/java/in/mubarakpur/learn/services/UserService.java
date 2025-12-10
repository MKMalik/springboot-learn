package in.mubarakpur.learn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import in.mubarakpur.learn.DTO.UserCreateDTO;
import in.mubarakpur.learn.DTO.UserDTO;
import in.mubarakpur.learn.mapper.UserCreateMapper;
import in.mubarakpur.learn.mapper.UserMapper;
import in.mubarakpur.learn.model.User;
import in.mubarakpur.learn.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserDTO getUser(long id) {
    return userRepository.findById(id).map(UserMapper::toDTO)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  public User addUser(UserCreateDTO user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "username taken");
    }
    User userEntity = UserCreateMapper.toEntity(user);
    userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(userEntity);
  }

  public void removeUser(long id) {
    if (!userRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    userRepository.deleteById(id);
  }

  public UserDTO updateUser(long id, User updatedUser) {
    User user = userRepository.findById(id)
        .map(u -> {
          u.setUsername(updatedUser.getUsername());
          u.setPassword(updatedUser.getPassword());
          return userRepository.save(u);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return UserMapper.toDTO(user);
  }

  public List<UserDTO> getUsers() {
    return userRepository.findAll()
        .stream()
        .map(UserMapper::toDTO)
        .toList();
  }
}
