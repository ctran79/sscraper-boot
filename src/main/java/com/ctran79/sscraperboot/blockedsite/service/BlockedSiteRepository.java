package com.ctran79.sscraperboot.blockedsite.service;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.blockedsite.model.BlockedSite;
import com.ctran79.sscraperboot.topic.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ctran79
 */

@Repository
public interface BlockedSiteRepository extends JpaRepository<BlockedSite, Integer> {
    BlockedSite findFirstBySite(String site);
}
