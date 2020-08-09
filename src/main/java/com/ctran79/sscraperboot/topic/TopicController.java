package com.ctran79.sscraperboot.topic;

import com.ctran79.sscraperboot.common.BaseController;
import com.ctran79.sscraperboot.dto.request.FormTopicList;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ctran79
 */

@Controller
@AllArgsConstructor
public class TopicController extends BaseController {

    public static final String ACCOUNT_PAGE = "account";

    private TopicService topicService;

    @PostMapping(value = "/topics", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String batchUpdateTopicsList(FormTopicList form) {
        topicService.batchUpdateTopicsList(form.getTopicsList(), form.getDeletedTopicIds());
        return redirect("/" + ACCOUNT_PAGE);
    }
}
