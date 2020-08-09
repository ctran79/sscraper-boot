package com.ctran79.sscraperboot.dto.request;

import com.ctran79.sscraperboot.topic.model.Topic;
import lombok.Data;

import java.util.List;

/**
 * @author ctran79
 */

@Data
public class FormTopicList {
    private List<Topic> topicsList;
    private Integer[] deletedTopicIds;
}
