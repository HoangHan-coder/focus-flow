package dev.focusflow.services;

import dev.focusflow.dto.request.UserRegisterDTO;
import dev.focusflow.entities.User;
import dev.focusflow.mapper.UserMapper;
import dev.focusflow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public User register(UserRegisterDTO userRegisterDTO) {
        User user = userMapper.toEntity(userRegisterDTO);
        return userRepository.save(user);
    }
}
