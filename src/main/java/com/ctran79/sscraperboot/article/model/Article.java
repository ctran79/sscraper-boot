package com.ctran79.sscraperboot.article.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author ctran79
 */

@Entity
@Data
public class Article {

    @Id
    private Integer id;

    @Column
    private String link;

    @Column
    private String title;

    @Column
    private String note;

    @Column
    private Date scrapingDate;

    @Column
    private boolean favorite;

    @Column
    private String attachment;

    @Column
    private boolean visited;

    @Column
    private boolean deleted;
}
