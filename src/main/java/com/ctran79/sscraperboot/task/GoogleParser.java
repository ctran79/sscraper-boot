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
public final class GoogleParser extends ParserBase {

    @Value("${sscraper}")
    @Getter
    private boolean enable;

    @Autowired
    public GoogleParser(ArticleService articleService, TopicService topicService, BlockedSiteRepository blockedSiteRepository) {
        super(articleService, topicService, blockedSiteRepository);
    }

    @Override
    public Parser getSourceName() {
        //ex.: https://www.google.pl/search?q=Keyword&lr=lang_pl&tbs=lr:lang_1pl,qdr:d&start=0&num=50
        return Parser.GOOGLE;
    }

    @Override
    List<Article> parseSearchResults(Topic topic) {
        List<Article> res = new ArrayList<>();
        try {
            if (topic.isEnabled()) {
                Document doc = Jsoup.connect(topic.getLink()).userAgent("Mozilla/5.0").get();
                Elements elms = doc.select("a > h3");

                for (Element result : elms) {
                    Element title = result.getElementsByTag("h3").first().getElementsByTag("div").first();
                    String linkHref = result.parentNode().attr("href");

                    Article article = newArticle(topic, title.text(),
                            linkHref.substring(7, linkHref.indexOf("&"))
                                    .replace("%3F", "?")
                                    .replace("%3D", "=")
                                    .replace("%26", "&")
                                    .replace("%25", "%")
                                    .replace("%2B", "+"));
                    if (article != null) {
                        res.add(article);
                    }
                }
            }
            return res;
        } catch (IOException e) {
            log.error("IOException while parsing link: " + topic.getLink(), e);
            return new ArrayList<>();
        }
    }
}
