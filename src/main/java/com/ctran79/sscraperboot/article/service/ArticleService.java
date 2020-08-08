package com.ctran79.sscraperboot.article.service;

import com.ctran79.sscraperboot.article.model.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    Integer countUnreadArticles();

    Page<Article> getArticlesListInTopic(Integer topicId, Integer pageNum, Integer pageSize);

    void saveAndFlush(Article article);

    Article getArticleByLink(String link);

    void patchArticle(Integer articleId, Boolean favorite, Boolean visited, Boolean deleted);
}
