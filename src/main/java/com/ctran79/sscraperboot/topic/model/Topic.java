package com.ctran79.sscraperboot.topic.model;

import com.ctran79.sscraperboot.article.model.Article;
import com.ctran79.sscraperboot.base.IdEntity;
import com.ctran79.sscraperboot.user.model.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ctran79
 */

@Entity
@Data
public class Topic extends IdEntity {

    @Column
    private String name;

    @Column
    private String link;

    @Column
    private String parser;

    @Column
    private boolean enabled = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "topic_role")
    @Column(name = "role")
    @JsonIgnore
    private Set<String> roles = new HashSet<>();
}
