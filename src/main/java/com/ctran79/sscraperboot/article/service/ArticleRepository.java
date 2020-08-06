package com.ctran79.sscraperboot.article.service;

import com.ctran79.sscraperboot.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ctran79
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Integer countByDeletedIsFalseAndVisitedIsFalse();
}
