package com.akamai.akamaiconnect.repository;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocialNetworkPostRepository extends JpaRepository<SocialNetworkPost, Long> {

    List<SocialNetworkPost> findByAuthor(String author);

    @Query("SELECT p FROM SocialNetworkPost p WHERE p.postcategory = :postcategory ORDER BY p.viewcount DESC")
    List<SocialNetworkPost> findTopPostsPerCategory(@Param("postcategory") String postcategory, Pageable pageable);
}
