package com.ctran79.sscraperboot.topic.service;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.topic.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author ctran79
 */

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT t.* FROM topic t JOIN topic_role tr ON t.id=tr.topic_id WHERE t.parser=:parser AND tr.role IN :roles")
    List<Topic> getTopicsByParser(@Param("parser") String parserCode, Set<String> roles);

    List<Topic> findTopicsByRolesInOrderByIdAsc(Set<String> roles);

    void deleteByIdIn(Integer[] deletedTopicIds);
}
