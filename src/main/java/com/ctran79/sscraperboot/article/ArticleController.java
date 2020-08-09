package com.ctran79.sscraperboot.article;

import com.ctran79.sscraperboot.article.service.ArticleService;
import com.ctran79.sscraperboot.common.BaseController;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @author ctran79
 */

@Controller
@AllArgsConstructor
public class ArticleController extends BaseController {

    public static final String ARTICLE_LIST_PAGE = "article-list";

    private TopicService topicService;

    private ArticleService articleService;

    @ModelAttribute
    void currentUrl(HttpServletRequest request,
                    Model model) {
        String url = request.getRequestURL().toString();
        if (!StringUtils.isEmpty(request.getQueryString())) {
            url += ("?" + request.getQueryString());
        }
        model.addAttribute("url", url);
    }

    @GetMapping("/parsers/{parserCode}")
    String articlesList(@PathVariable String parserCode,
                         Model model, Authentication auth) {
        parserCode = parserCode.toUpperCase();
        Set<String> roles = getLoggedInUserRoles(auth);
        List<Topic> topics = topicService.getTopicsByParserAndRoles(parserCode, roles);

        model.addAttribute("parserCode", parserCode.toLowerCase());
        model.addAttribute("topics", topics);

        return ARTICLE_LIST_PAGE;
    }
}
