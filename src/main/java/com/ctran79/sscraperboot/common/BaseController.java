package com.ctran79.sscraperboot.common;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

public class BaseController {

    public String redirect(String url) {
        return "redirect:" + url;
    }

    public Set<String> getLoggedInUserRoles(Authentication auth) {
        return auth.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet());
    }
}
