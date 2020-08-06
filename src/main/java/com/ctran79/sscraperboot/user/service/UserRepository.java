package com.ctran79.sscraperboot.user.service;

import com.ctran79.sscraperboot.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author ctran79
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);
}
