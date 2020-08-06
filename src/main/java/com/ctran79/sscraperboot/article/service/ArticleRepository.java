package com.ctran79.sscraperboot.article.service;

import com.ctran79.sscraperboot.article.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ctran79
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Integer countByDeletedIsFalseAndVisitedIsFalse();

    @Query(nativeQuery = true,
            value = "SELECT a.* FROM article a JOIN article_topic at ON a.id=at.article_id WHERE at.topic_id=?1 AND a.deleted=false")
    List<Article> getArticlesListInTopic(Integer topicId, Pageable pageable);

    Article findArticleByLinkEquals(String link);
}
