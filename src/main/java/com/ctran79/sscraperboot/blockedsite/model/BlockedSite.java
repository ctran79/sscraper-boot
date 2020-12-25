package com.ctran79.sscraperboot.blockedsite.model;

import com.ctran79.sscraperboot.base.IdEntity;
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
public class BlockedSite extends IdEntity {

    @Column
    private String site;
}
