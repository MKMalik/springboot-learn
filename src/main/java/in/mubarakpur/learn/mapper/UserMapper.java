package in.mubarakpur.learn.mapper;

import in.mubarakpur.learn.DTO.UserDTO;
import in.mubarakpur.learn.model.User;

/**
 * UserMapper
 */
public class UserMapper {

  public static UserDTO toDTO(User user) {
    UserDTO dto = new UserDTO();
    dto.setId(user.getId());
    dto.setUsername(user.getUsername());
    dto.setCreatedAt(user.getCreatedAt());
    return dto;
  }
}
