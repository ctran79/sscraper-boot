package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.topic.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.*;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

     @Autowired
     private TopicRepository repository;

    @Test
    void getTopicsByParser() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        List<Topic> topics = repository.getTopicsByParser("GOOGLE", roles);
        Assert.notEmpty(topics, "Topics not found");
    }
}