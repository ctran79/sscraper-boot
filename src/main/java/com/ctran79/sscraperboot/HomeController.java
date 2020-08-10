package com.ctran79.sscraperboot;

import com.ctran79.sscraperboot.article.service.ArticleService;
import com.ctran79.sscraperboot.base.BaseController;
import com.ctran79.sscraperboot.user.model.User;
import com.ctran79.sscraperboot.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

/**
 * @author ctran79
 */

@Controller
@AllArgsConstructor
public class HomeController extends BaseController {

    public static final String INDEX_PAGE = "dashboard";

    private UserService userService;

    private ArticleService articleService;

    @GetMapping(path = "/")
    String index(Model model, Authentication auth) {
        Set<String> roles = getLoggedInUserRoles(auth);
        User user = userService.getLoggedUser(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("nUnreadArticles", articleService.countUnreadArticles(roles));
        return INDEX_PAGE;
    }
}