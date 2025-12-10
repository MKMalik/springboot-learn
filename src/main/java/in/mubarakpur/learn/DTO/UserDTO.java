package in.mubarakpur.learn.DTO;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * UserDTO
 */
@Data
public class UserDTO {

  private int id;
  @NotBlank(message = "username is required")
  @NotNull
  private String username;
  @NotNull(message = "createdAt is non nullable")
  private Date createdAt;

  public UserDTO() {
  }

  @NonNull
  public int getId() {
    return id;
  }

  @NonNull
  public String getUsername() {
    return username;
  }

  @NonNull
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
