package com.ctran79.sscraperboot.dto.response;

import com.ctran79.sscraperboot.article.model.Article;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ctran79
 */

@Data
public class DataTableResult {

    private final List<Article> data;
    private final long recordsTotal;
    private final long recordsFiltered;

    public DataTableResult(Page<Article> page) {
        this.data = page.getContent();
        this.recordsTotal = page.getTotalElements();
        this.recordsFiltered = page.getTotalElements();
    }
}
