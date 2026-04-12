package dev.focusflow.mapper;

import dev.focusflow.dto.request.UserRegisterDTO;
import dev.focusflow.entities.User;
import dev.focusflow.enums.Role;
import dev.focusflow.enums.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserRegisterDTO dto) {
        return new User(
                passwordEncoder.encode(dto.getPassword()),
                dto.getEmail().toLowerCase().trim(),
                dto.getFullName().trim(),
                Role.User,
                UserStatus.ACTIVE,
                LocalDateTime.now()
        );
    }
}
