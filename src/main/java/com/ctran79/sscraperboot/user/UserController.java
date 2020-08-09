package com.ctran79.sscraperboot.user;

import com.ctran79.sscraperboot.base.BaseController;
import com.ctran79.sscraperboot.task.Parser;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

@Controller
@AllArgsConstructor
public class UserController extends BaseController {

    public static final String LOGIN_PAGE = "login";
    public static final String ACCOUNT_PAGE = "account";

    private TopicService topicService;

    @GetMapping("/login")
    String login() {
        return LOGIN_PAGE;
    }

    @GetMapping("/account")
    String account(Authentication auth, Model model) {
        Set<String> roles = getLoggedInUserRoles(auth);
        List<Topic> topics = topicService.getTopicsByRoles(roles);
        model.addAttribute("topicsList", topics);
        model.addAttribute("parsersList", Arrays.stream(Parser.values()).map(Parser::name).collect(Collectors.toList()));
        return ACCOUNT_PAGE;
    }
}
