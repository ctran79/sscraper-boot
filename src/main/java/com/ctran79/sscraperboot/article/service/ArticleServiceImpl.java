package com.ctran79.sscraperboot.article.service;

import com.ctran79.sscraperboot.article.model.Article;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<Article> getArticlesListInTopic(Integer topicId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Order.desc("id")));
        return articleRepository.getArticlesListInTopic(topicId, pageable);
    }

    @Override
    public void saveAndFlush(Article article) {
        articleRepository.saveAndFlush(article);
    }

    @Override
    public Article getArticleByLink(String link) {
        return articleRepository.findArticleByLinkEquals(link);
    }

    @Override
    public void patchArticle(Integer articleId, Boolean favorite, Boolean visited, Boolean deleted) {
        Article article = articleRepository.getOne(articleId);
        if (article == null) {
            return;
        }
        boolean changed = (favorite != null || visited != null || deleted != null);
        if (favorite != null) {
            article.setFavorite(favorite);
        }
        if (visited != null) {
            article.setVisited(visited);
        }
        if (deleted != null) {
            article.setDeleted(deleted);
        }
        if (changed) {
            articleRepository.save(article);
        }
    }
}
