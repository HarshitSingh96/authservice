package dev.harshit.userservice.services;

import dev.harshit.userservice.dtos.SignupRequestDto;
import dev.harshit.userservice.dtos.UserDto;
import dev.harshit.userservice.models.User;
import dev.harshit.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto signup(SignupRequestDto signupRequestDto) {
        User user = new User();
        user.setEmailId(signupRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDto.getPassword()));
        User user1 = userRepository.save(user);
        return UserDto.from(user1);
    }
}
