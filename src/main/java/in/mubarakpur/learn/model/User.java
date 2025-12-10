package in.mubarakpur.learn.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
  name = "users",
  uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int id;
  @Column(nullable = false, unique = true, length = 255)
  private String username;
  @Column(nullable = false)
  private String password;
  @CreationTimestamp
  private Date createdAt;

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
