package com.ctran79.sscraperboot.user.service;

import com.ctran79.sscraperboot.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author ctran79
 */

public interface UserService  {
    User getLoggedUser(String username);
}
