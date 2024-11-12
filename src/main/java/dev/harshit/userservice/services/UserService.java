package dev.harshit.userservice.services;

import dev.harshit.userservice.dtos.UserDto;
import dev.harshit.userservice.models.Role;
import dev.harshit.userservice.models.User;
import dev.harshit.userservice.repositories.RoleRepository;
import dev.harshit.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto getUserDetails(Long userId) {
        System.out.println("I got the request");
        return new UserDto();
//        Optional<User> userOptional = userRepository.findById(userId);
//
//        if (userOptional.isEmpty()) {
//            return null;
//        }
//
//        return UserDto.from(userOptional.get());
    }

    public UserDto setUserRoles(Long userId, List<Long> roleIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
//        user.setRoles(Set.copyOf(roles));

        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }
}
