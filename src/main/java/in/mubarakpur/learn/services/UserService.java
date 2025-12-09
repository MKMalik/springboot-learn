package in.mubarakpur.learn.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import in.mubarakpur.learn.model.User;

@Service
public class UserService {

  List<User> users = new ArrayList<>();

  public UserService() {
    users.add(new User(1, "mkmalik", "password123", new Date()));
    users.add(new User(2, "meow", "meow123", new Date()));
    users.add(new User(3, "kitten", "meowmeow", new Date()));
  }

  public User getUser(int id) throws ResponseStatusException {
    User userVal = this.getUsers().stream()
        .filter((user) -> user.getId() == id)
        .findAny().orElse(null);
    if (userVal == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    return userVal;
  }

  public User addUser(User user) throws ResponseStatusException {
    // check id, username uniqueness
    boolean exist = this.getUsers().stream()
        .anyMatch(
            (filterUser) -> (filterUser.getId() == user.getId() || filterUser.getUsername() == user.getUsername()));
    if (exist) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User id or username already exists");
    }
    user.setCreatedAt(new Date());
    this.users.add(user);
    return user;
  }

  public void removeUser(int id) throws ResponseStatusException {
    boolean removed = this.getUsers().removeIf((user) -> user.getId() == id);
    if (!removed) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  public User updateUser(User user) throws ResponseStatusException {
    User fetchedUser = this.getUsers().stream().filter((usr) -> user.getId() == user.getId()).findAny().get();
    if (fetchedUser == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    fetchedUser.setUsername(user.getUsername());
    fetchedUser.setPassword(user.getPassword());
    return fetchedUser;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
