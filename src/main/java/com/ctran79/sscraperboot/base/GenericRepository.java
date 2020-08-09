package com.ctran79.sscraperboot.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author ctran79
 */

@Repository
public interface GenericRepository extends JpaRepository<IdEntity, Integer> {
    @Query(nativeQuery = true,
            value = "DELETE FROM article_topic WHERE topic_id=?1 " +
                    "AND article_id IN (SELECT id FROM article WHERE deleted=true)"
    )
    @Modifying
    void deleteArticleTopicRelation(Integer topicId);
}
