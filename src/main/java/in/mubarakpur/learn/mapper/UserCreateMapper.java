package in.mubarakpur.learn.mapper;

import java.util.Date;

import in.mubarakpur.learn.DTO.UserCreateDTO;
import in.mubarakpur.learn.model.User;

/**
 * UserCreateMapper
 */
public class UserCreateMapper {

   public static User toEntity(UserCreateDTO dto) {
    User user = new User();
    user.setUsername(dto.getUsername());
    user.setPassword(dto.getPassword());
    return user;
  }
}
