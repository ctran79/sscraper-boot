package com.ctran79.sscraperboot.common;

/**
 * @author ctran79
 */

public class BaseController {

    public String redirect(String url) {
        return "redirect:" + url;
    }
}
