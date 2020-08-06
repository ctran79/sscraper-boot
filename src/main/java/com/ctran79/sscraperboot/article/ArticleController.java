package com.ctran79.sscraperboot.article;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.article.service.ArticleService;
import com.ctran79.sscraperboot.common.BaseController;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

@Controller
@AllArgsConstructor
public class ArticleController extends BaseController {

    public static final String ARTICLE_LIST_PAGE = "article-list";

    private TopicService topicService;

    private ArticleService articleService;

    @GetMapping("parsers/{parserCode}")
    String articlesList(@PathVariable String parserCode,
                        @RequestParam(required = false, name = "topic") Integer topicId,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        Model model, Authentication auth) {
        parserCode = parserCode.toUpperCase();
        Set<String> roles = auth.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet());
        List<Topic> topics = topicService.getTopicsByParser(parserCode, roles);
        boolean hasAccess2Topic = topics.stream().anyMatch(topic -> topic.getId() == topicId);
        if (topicId != null && hasAccess2Topic) {
            List<Article> articlesList = articleService.getArticlesListInTopic(topicId, pageNum);
            model.addAttribute("articlesList", articlesList);
        }
        model.addAttribute("parserCode", parserCode.toLowerCase());
        model.addAttribute("topics", topics);

        return ARTICLE_LIST_PAGE;
    }
}
