package com.ctran79.sscraperboot.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ctran79
 */

public class BaseController {

    public String redirect(String url) {
        return "redirect:" + url;
    }
}
