package com.ctran79.sscraperboot.article.service;

import com.ctran79.sscraperboot.article.model.Article;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ctran79
 */

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Override
    public Integer countUnreadArticles() {
        return articleRepository.countByDeletedIsFalseAndVisitedIsFalse();
    }

    @Override
    public List<Article> getArticlesListInTopic(Integer topicId, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 20, Sort.by(Sort.Order.desc("id")));
        return articleRepository.getArticlesListInTopic(topicId, pageable);
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Article getArticleByLink(String link) {
        return articleRepository.findArticleByLinkEquals(link);
    }
}
