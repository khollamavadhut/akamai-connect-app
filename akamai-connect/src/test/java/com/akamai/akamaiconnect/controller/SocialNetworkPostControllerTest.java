package com.akamai.akamaiconnect.controller;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import com.akamai.akamaiconnect.service.SocialNetworkPostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class SocialNetworkPostControllerTest {

    @Mock
    private SocialNetworkPostService postService;

    @InjectMocks
    private SocialNetworkPostController postController;

    @Test
    public void testGetAllPosts() {
        List<SocialNetworkPost> mockPosts = getMockData();

        Mockito.when(postService.getAllPosts()).thenReturn(mockPosts);

        ResponseEntity<List<SocialNetworkPost>> response = postController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPosts, response.getBody());
    }

    @Test
    public void testGetTopPostsPerCategory() {
        List<SocialNetworkPost> mockTopPosts = getMockData();

        Mockito.when(postService.getTopPostsPerCategory("Technology", 10)).thenReturn(mockTopPosts);

        ResponseEntity<List<SocialNetworkPost>> response = postController.getTopPostsPerCategory("Technology", 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTopPosts, response.getBody());
    }

    @Test
    public void testSearchPostsByAuthor() {
        String authorName = "Author1";
        List<SocialNetworkPost> mockPosts = getMockData();

        Mockito.when(postService.searchPostsByAuthor(authorName)).thenReturn(mockPosts);

        ResponseEntity<List<SocialNetworkPost>> response = postController.searchPostsByAuthor(authorName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPosts, response.getBody());
    }

    @Test
    public void testSearchPostsByAuthorNotFound() {
        String authorName = "NonExistingAuthor";

        Mockito.when(postService.searchPostsByAuthor(authorName)).thenReturn(new ArrayList<>());

        ResponseEntity<List<SocialNetworkPost>> response = postController.searchPostsByAuthor(authorName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testInternalServerError() {
        Mockito.when(postService.getAllPosts()).thenThrow(RuntimeException.class);

        ResponseEntity<List<SocialNetworkPost>> response = postController.getAllPosts();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    private List<SocialNetworkPost> getMockData(){

        List<SocialNetworkPost> mockTopPosts = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        SocialNetworkPost post1 = new SocialNetworkPost(1L, now.minusDays(1), "Technology", "User1", "Content of post 1", 100);
        SocialNetworkPost post2 = new SocialNetworkPost(2L, now.minusDays(2), "Science", "User2", "Content of post 2", 150);
        SocialNetworkPost post3 = new SocialNetworkPost(3L, now.minusDays(3), "Technology", "User3", "Content of post 3", 120);

        mockTopPosts.add(post1);
        mockTopPosts.add(post2);
        mockTopPosts.add(post3);

        return mockTopPosts;
    }
}
