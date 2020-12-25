package com.ctran79.sscraperboot.task;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.article.service.ArticleService;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import com.ctran79.sscraperboot.user.model.RoleType;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public abstract class ParserBase implements ParserService {

    private static final WebClient webClient = new WebClient();

    private ArticleService articleService;
    private TopicService topicService;

    static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Cannot sleep because of IOException", e);
        }
    }

    @Scheduled(fixedDelay = 6 * 60 * 60 * 1000, initialDelay = 0) //every 6 hours
    @Override
    public void scrape() {
        if (isEnable()) {
            Set<String> allRoles = Stream.of(RoleType.values())
                    .map(roleType -> roleType.name())
                    .collect(Collectors.toSet());
            List<Topic> topics = topicService.getTopicsByParserAndRoles(getSourceName().name(), allRoles);
            for (Topic topic : topics) {
                log.info("Parsing link: " + topic.getLink());
                List<Article> articles = parseSearchResults(topic);
                Set<String> links = new HashSet<>();
                articles = articles.stream()
                        .filter(article -> {
                            boolean inList = links.contains(article.getLink());
                            if (!inList) {
                                links.add(article.getLink());
                            }
                            return !inList;
                        })
                        .map(article -> {
//                            String title = tryParseTitle(article.getLink());
//                            if (!StringUtils.isEmpty(title)) {
//                                article.setTitle(title);
//                            }
                            return article;
                        })
                        .collect(Collectors.toList());

                for (Article article : articles) {
                    Article existingArticle = articleService.getArticleByLink(article.getLink());
                    if (existingArticle == null) {
                        articleService.saveAndFlush(article);
                    }
                }
                log.info("Done: " + topic.getLink());
//                pause(6000);
            }
        } else {
            log.info("Parser task is turned off");
        }
    }


    private String tryParseTitle(String link) {
        try {
            final HtmlPage page = webClient.getPage(link);
            return page.getTitleText();
        } catch (Throwable e) {
            return null;
        }
    }

    Article newArticle(Topic topic, String title, String link) {
        Article article = new Article();
        article.getTopics().add(topic);
        article.setTitle(title);
        article.setLink(link);
        Integer p = link.indexOf("://") + 3;
        article.setSite(link.substring(0, link.indexOf("/", p)));
        return article;
    }

    abstract List<Article> parseSearchResults(Topic topic);
}
