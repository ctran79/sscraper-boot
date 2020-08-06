package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.topic.model.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author ctran79
 */

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    @Override
    public List<Topic> getTopicsByParser(String parserCode, Set<String> roles) {
        return topicRepository.getTopicsByParser(parserCode, roles);
    }
}
