package com.ctran79.sscraperboot.task;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.article.service.ArticleService;
import com.ctran79.sscraperboot.blockedsite.service.BlockedSiteRepository;
import com.ctran79.sscraperboot.topic.model.Topic;
import com.ctran79.sscraperboot.topic.service.TopicService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public final class NajwiParser extends ParserBase {

    @Value("${sscraper}")
    @Getter
    private boolean enable;

    @Autowired
    public NajwiParser(ArticleService articleService, TopicService topicService, BlockedSiteRepository blockedSiteRepository) {
        super(articleService, topicService, blockedSiteRepository);
    }

    @Override
    public Parser getSourceName() {
        //ex.: https://www.najwi.pl/Keyword1-Keyword2?newsList-vp-page=2
        return Parser.NAJWI;
    }

    @Override
    List<Article> parseSearchResults(Topic topic) {
        List<Article> res = new ArrayList<>();
        if (topic.isEnabled()) {
            for (int page = 1; page <= 6; page++) {
                try {
                    Document doc = Jsoup.connect(topic.getLink() + page).userAgent("Mozilla/5.0").get();
                    Elements elms = doc.select("td > h2 > a");

                    for (Element a : elms) {
                        String linkHref = a.attr("href");
                        Article article = newArticle(topic, a.text(), linkHref);
                        if (article != null) {
                            res.add(article);
                        }
                    }
                } catch (IOException e) {
                    log.error("IOException while parsing link: " + topic.getLink() + page, e);
                }
            }
        }
        return res;
    }
}
