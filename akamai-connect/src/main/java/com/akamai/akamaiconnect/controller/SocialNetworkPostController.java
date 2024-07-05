package com.akamai.akamaiconnect.controller;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import com.akamai.akamaiconnect.service.SocialNetworkPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class SocialNetworkPostController {

    private final SocialNetworkPostService postService;

    @Autowired
    public SocialNetworkPostController(SocialNetworkPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<SocialNetworkPost>> getAllPosts() {
        try {
            List<SocialNetworkPost> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/top/{count}")
    public ResponseEntity<List<SocialNetworkPost>> getTopPostsPerCategory(@RequestParam String postcategory, @PathVariable int count) {
        try {
            List<SocialNetworkPost> topPosts = postService.getTopPostsPerCategory(postcategory, count);
            return ResponseEntity.ok(topPosts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SocialNetworkPost>> searchPostsByAuthor(@RequestParam String author) {
        try {
            List<SocialNetworkPost> posts = postService.searchPostsByAuthor(author);
            if (posts.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
