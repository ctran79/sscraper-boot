package com.ctran79.sscraperboot.topic.model;

import com.ctran79.sscraperboot.user.model.RoleType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ctran79
 */

@Entity
@Data
public class Topic {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String link;

    @Column
    private String parser;

    @ElementCollection
    @Column(name = "role")
    @CollectionTable(name = "topic_role")
    private Set<RoleType> roles;
}
