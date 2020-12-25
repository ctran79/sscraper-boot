package com.ctran79.sscraperboot;

import com.ctran79.sscraperboot.user.model.User;
import com.ctran79.sscraperboot.user.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class SscraperBootApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateUser() {
        User user = new User();

        user.setEmail(null);
        user.setFullName("Chungss");
        user.setPassword("sss");
        user.setUsername("sass");
        userRepository.save(user);
    }
}
