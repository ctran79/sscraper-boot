package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.topic.model.Topic;

import java.util.List;
import java.util.Set;

/**
 * @author ctran79
 */

public interface TopicService {

    List<Topic> getTopicsByParser(String parserCode, Set<String> roles);
}
