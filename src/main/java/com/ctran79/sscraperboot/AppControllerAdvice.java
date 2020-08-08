package com.ctran79.sscraperboot;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ctran79
 */

@ControllerAdvice
public class AppControllerAdvice {

    @ModelAttribute("baseContext")
    String getBaseUrl(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }
}
