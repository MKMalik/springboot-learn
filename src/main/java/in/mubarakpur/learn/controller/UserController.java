package in.mubarakpur.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.mubarakpur.learn.model.User;
import in.mubarakpur.learn.services.UserService;
import jakarta.websocket.server.PathParam;

@RestController
class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/users")
  List<User> listUsers() {
    return userService.getUsers();
  }

  @GetMapping("/user")
  User getUser(
      @RequestParam int id) {
    return userService.getUser(id);
  }

  @PostMapping("user")
  User addUser(@RequestBody User user) {
    userService.addUser(user);
    return user;
  }

  @PutMapping("/user/{id}")
  User updateUser(@RequestBody User user, @PathVariable Long id) {
    return this.userService.updateUser(id, user);
  }

  @DeleteMapping("user")
  void deleteUser(@RequestParam int id) {
    userService.removeUser(id);
  }
}
