package com.ctran79.sscraperboot.article.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ctran79
 */

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private ArticleRepository articleRepository;

    @Override
    public Integer countUnreadArticles() {
        return articleRepository.countByDeletedIsFalseAndVisitedIsFalse();
    }
}
