package com.ctran79.sscraperboot.article.model;

import com.ctran79.sscraperboot.topic.model.Topic;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ctran79
 */

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String link;

    @Column
    private String title;

    @Column
    private String note;

    @Column
    private Date scrapingDate = new Date();

    @Column
    private boolean favorite = false;

    @Column
    private String attachment;

    @Column
    private boolean visited = false;

    @Column
    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "article_topic",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics = new HashSet<>();
}
