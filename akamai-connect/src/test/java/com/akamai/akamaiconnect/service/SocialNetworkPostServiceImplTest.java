package com.akamai.akamaiconnect.service;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import com.akamai.akamaiconnect.repository.SocialNetworkPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SocialNetworkPostServiceImplTest {

    @Mock
    private SocialNetworkPostRepository postRepository;

    @InjectMocks
    private SocialNetworkPostServiceImpl postService;

    @Test
    public void testGetAllPosts() {
        List<SocialNetworkPost> mockPosts = getMockData();

        when(postRepository.findAll()).thenReturn(mockPosts);

        List<SocialNetworkPost> result = postService.getAllPosts();

        assertEquals(mockPosts.size(), result.size());
        assertEquals(mockPosts, result);
    }

    @Test
    public void testGetTopPostsPerCategory() {
        List<SocialNetworkPost> mockPosts = getMockData();

        when(postRepository.findTopPostsPerCategory("Technology", PageRequest.of(0, 10))).thenReturn(mockPosts);

        List<SocialNetworkPost> result = postService.getTopPostsPerCategory("Technology", 10);

        assertEquals(mockPosts.size(), result.size());
        assertEquals(mockPosts, result);
    }

    @Test
    public void testSearchPostsByAuthor() {
        String author = "Author1";
        List<SocialNetworkPost> mockPostsByAuthor = new ArrayList<>();

        SocialNetworkPost post1 = new SocialNetworkPost(1L, LocalDateTime.now(), "Technology", author, "Content of post 1", 100);

        mockPostsByAuthor.add(post1);

        when(postRepository.findByAuthor(author)).thenReturn(mockPostsByAuthor);

        List<SocialNetworkPost> result = postService.searchPostsByAuthor(author);

        assertEquals(mockPostsByAuthor.size(), result.size());
        assertEquals(mockPostsByAuthor, result);
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
