package com.ctran79.sscraperboot.topic;

import com.ctran79.sscraperboot.base.BusinessException;
import com.ctran79.sscraperboot.base.BaseController;
import com.ctran79.sscraperboot.dto.request.FormTopicList;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

/**
 * @author ctran79
 */

@Controller
@AllArgsConstructor
public class TopicController extends BaseController {

    public static final String ACCOUNT_PAGE = "account";

    private TopicService topicService;

    @PostMapping(value = "/topics", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String batchUpdateTopicsList(FormTopicList form, Authentication auth, RedirectAttributes redirectAttributes) {
        Set<String> roles = getLoggedInUserRoles(auth);
        form.getTopicsList().stream().forEach(topic -> topic.setRoles(roles));
        try {
            topicService.batchUpdateTopicsList(form.getTopicsList(), form.getDeletedTopicIds());
            redirectAttributes.addFlashAttribute("success", "Success");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return redirect("/" + ACCOUNT_PAGE);
    }
}
