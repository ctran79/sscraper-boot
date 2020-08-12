package com.ctran79.sscraperboot.article;

import com.ctran79.sscraperboot.ApiController;
import com.ctran79.sscraperboot.dto.response.DataTableResult;
import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.article.service.ArticleService;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

@ApiController
@AllArgsConstructor
public class RestArticleController {

    private ArticleService articleService;

    private TopicService topicService;

    @PatchMapping("/articles/{articleId}")
    ResponseEntity<Void> patchArticle(@PathVariable Integer articleId,
                                      @RequestParam(required = false) Boolean favorite,
                                      @RequestParam(required = false) Boolean visited,
                                      @RequestParam(required = false) Boolean deleted) {
        articleService.patchArticle(articleId, favorite, visited, deleted);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/parsers/{parserCode}")
    ResponseEntity<DataTableResult> articlesList(@PathVariable String parserCode,
                                               @RequestParam(name = "topic") Integer topicId,
                                               @RequestParam Integer start,
                                               @RequestParam Integer length,
                                               Authentication auth) {
        parserCode = parserCode.toUpperCase();
        Set<String> roles = auth.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet());
        List<Topic> topics = topicService.getTopicsByParserAndRoles(parserCode, roles);
        boolean hasAccess2Topic = topics.stream().anyMatch(topic -> topic.getId() == topicId);
        if (topicId != null && hasAccess2Topic) {
            Page<Article> page = articleService.getArticlesListInTopic(topicId, start/length, length);
            return ResponseEntity.ok(new DataTableResult(page));
        }
        return ResponseEntity.badRequest().build();
    }
}
