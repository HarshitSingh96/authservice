package dev.harshit.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {

    private Long userId;
    private String token;
}
