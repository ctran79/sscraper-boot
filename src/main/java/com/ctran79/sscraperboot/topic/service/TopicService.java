package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.base.BusinessException;
import com.ctran79.sscraperboot.topic.model.Topic;

import java.util.List;
import java.util.Set;

/**
 * @author ctran79
 */

public interface TopicService {

    List<Topic> getTopicsByParserAndRoles(String parserCode, Set<String> roles);

    List<Topic> getTopicsByRoles(Set<String> roles);

    void batchUpdateTopicsList(List<Topic> topicsList, Integer[] deletedTopicIds) throws BusinessException;
}
