package com.ctran79.sscraperboot.article;

import com.ctran79.sscraperboot.common.BaseController;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import com.ctran79.sscraperboot.user.model.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
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

    @GetMapping("parsers/{parserCode}/articles")
    String articlesList(@PathVariable String parserCode, Model model) {
        parserCode = parserCode.toUpperCase();
        Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet());
        List<Topic> topics = topicService.getTopicsByParser(parserCode, roles);

        model.addAttribute("topics", topics);

        return ARTICLE_LIST_PAGE;
    }
}
