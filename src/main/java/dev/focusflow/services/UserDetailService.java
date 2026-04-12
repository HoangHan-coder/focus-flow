package dev.focusflow.services;

import dev.focusflow.entities.User;
import dev.focusflow.exceptions.DisabledException;
import dev.focusflow.records.UserDetail;
import dev.focusflow.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserDetailService.class);

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        log.info("Load user with email {}", user.getEmail());

        if (user.isDeleted()) {
            throw new DisabledException();
        }
        return new UserDetail(user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.isDeleted());
    }
}
