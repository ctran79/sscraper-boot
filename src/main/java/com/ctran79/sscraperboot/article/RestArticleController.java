package com.ctran79.sscraperboot.article;

import com.ctran79.sscraperboot.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author ctran79
 */

@RestController
@AllArgsConstructor
public class RestArticleController {

    private ArticleService articleService;

    @PatchMapping("articles/{articleId}")
    ResponseEntity<Void> patchArticle(@PathVariable Integer articleId,
                                @RequestParam(required = false) Boolean favorite,
                                @RequestParam(required = false) Boolean visited,
                                @RequestParam(required = false) Boolean deleted) {
        articleService.patchArticle(articleId, favorite, visited, deleted);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
