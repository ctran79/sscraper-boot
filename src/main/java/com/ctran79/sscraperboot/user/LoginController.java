package com.ctran79.sscraperboot.user;

import com.ctran79.sscraperboot.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ctran79
 */

@Controller
public class LoginController extends BaseController {

    public static final String LOGIN_PAGE = "login";
    public static final String INDEX_PAGE = "index";

    @GetMapping("/login")
    String renderLoginPage() {
        return LOGIN_PAGE;
    }

    @GetMapping("/")
    String index() {
        return INDEX_PAGE;
    }
}
