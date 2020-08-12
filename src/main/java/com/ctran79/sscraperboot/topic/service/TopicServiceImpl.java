package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.base.BusinessException;
import com.ctran79.sscraperboot.base.GenericRepository;
import com.ctran79.sscraperboot.article.service.ArticleRepository;
import com.ctran79.sscraperboot.topic.model.Topic;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ctran79
 */

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private ArticleRepository articleRepository;
    private GenericRepository genericRepository;

    @Override
    public List<Topic> getTopicsByParserAndRoles(String parserCode, Set<String> roles) {
        return topicRepository.getTopicsByParser(parserCode, roles);
    }

    @Override
    public List<Topic> getTopicsByRoles(Set<String> roles) {
        return topicRepository.findTopicsByRolesInOrderByIdAsc(roles);
    }

    @Transactional
    @Override
    public void batchUpdateTopicsList(List<Topic> topicsList, Integer[] deletedTopicIds) throws BusinessException {
        List<Topic> list2Save = new ArrayList<>();
        topicsList.stream().forEach(dto -> {
            Topic topic = (dto.getId() == null ? new Topic() : topicRepository.getOne(dto.getId()));
            topic.setName(dto.getName());
            topic.setLink(dto.getLink());
            topic.setParser(dto.getParser());
            topic.setEnabled(dto.isEnabled());
            topic.setRoles(dto.getRoles());
            list2Save.add(topic);
        });
        topicRepository.saveAll(list2Save);
        if (ArrayUtils.isNotEmpty(deletedTopicIds)) {
            for (Integer topicId : deletedTopicIds) {
                Topic topic = new Topic();
                topic.setId(topicId);
                if (articleRepository.existsArticleByDeletedIsFalseAndTopicsIs(topic)) {
                    throw new BusinessException("Cannot delete topic because of existing undeleted articles");
                } else {
                    genericRepository.deleteArticleTopicRelation(topicId);
                }
            }
            topicRepository.deleteByIdIn(deletedTopicIds);
        }
    }
}
