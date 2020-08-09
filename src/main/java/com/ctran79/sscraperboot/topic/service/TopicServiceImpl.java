package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.topic.model.Topic;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Topic> getTopicsByParserAndRoles(String parserCode, Set<String> roles) {
        return topicRepository.getTopicsByParser(parserCode, roles);
    }

    @Override
    public List<Topic> getTopicsByRoles(Set<String> roles) {
        return topicRepository.findTopicsByRolesIn(roles);
    }

    @Transactional
    @Override
    public void batchUpdateTopicsList(List<Topic> topicsList, Integer[] deletedTopicIds) {
        List<Topic> list2Save = new ArrayList<>();
        topicsList.stream().forEach(dto -> {
            Topic topic = dto.getId() == null ? new Topic() : topicRepository.getOne(dto.getId());
            topic.setName(dto.getName());
            topic.setLink(dto.getLink());
            topic.setParser(dto.getParser());
            list2Save.add(topic);
        });
        topicRepository.saveAll(list2Save);
        if (ArrayUtils.isNotEmpty(deletedTopicIds)) {
            topicRepository.deleteByIdIn(deletedTopicIds);
        }
    }
}
