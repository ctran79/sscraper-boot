package com.ctran79.sscraperboot.article.service;

import com.ctran79.sscraperboot.article.model.Article;

import java.util.List;

public interface ArticleService {
    Integer countUnreadArticles();

    List<Article> getArticlesListInTopic(Integer topicId, Integer pageNum);

    void save(Article article);

    Article getArticleByLink(String link);
}
