package com.ctran79.sscraperboot.user.service;

import com.ctran79.sscraperboot.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserService {

    private UserRepository userRepository;

    @Override
    public User getLoggedUser(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }
}
