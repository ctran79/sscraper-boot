package com.ctran79.sscraperboot.base;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ctran79
 */

@Data
@MappedSuperclass
public abstract class IdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
