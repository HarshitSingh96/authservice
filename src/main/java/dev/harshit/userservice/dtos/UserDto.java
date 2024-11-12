package dev.harshit.userservice.dtos;

import dev.harshit.userservice.models.Role;
import dev.harshit.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String email;

    private List<Role> roles;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmailId());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
