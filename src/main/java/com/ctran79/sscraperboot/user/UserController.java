package com.ctran79.sscraperboot.user;

import com.ctran79.sscraperboot.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ctran79
 */

@Controller
public class UserController extends BaseController {

    public static final String LOGIN_PAGE = "login";
    public static final String ACCOUNT_PAGE = "account";

    @GetMapping("/login")
    String login() {
        return LOGIN_PAGE;
    }

    @GetMapping("/account")
    String account() {
        return ACCOUNT_PAGE;
    }
}
