package in.mubarakpur.learn.model;

import java.util.Date;

public class User {

  int id;
  String username;
  String password;
  Date createdAt;

  public User() {
  }

  public User(final int id, final String username, final String password, Date createdAt) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }
}
