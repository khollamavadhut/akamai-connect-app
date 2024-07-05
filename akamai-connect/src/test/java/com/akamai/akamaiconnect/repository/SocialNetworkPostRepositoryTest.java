package com.akamai.akamaiconnect.repository;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SocialNetworkPostRepositoryTest {

    @Mock
    private SocialNetworkPostRepository postRepository;

    private LocalDateTime now = LocalDateTime.now();

    @Test
    public void testFindByAuthor() {
        String author = "Author1";
        List<SocialNetworkPost> mockPostsByAuthor = new ArrayList<>();
        mockPostsByAuthor.add(new SocialNetworkPost(1L, now.minusDays(1), "Technology", author, "Content 1", 100));
        mockPostsByAuthor.add(new SocialNetworkPost(2L, now.minusDays(1), "Science", author, "Content 2", 150));

        when(postRepository.findByAuthor(author)).thenReturn(mockPostsByAuthor);

        List<SocialNetworkPost> result = postRepository.findByAuthor(author);

        assertEquals(mockPostsByAuthor.size(), result.size());
        assertEquals(mockPostsByAuthor, result);
    }

    @Test
    public void testFindTopPostsPerCategory() {
        List<SocialNetworkPost> mockTopPosts = new ArrayList<>();
        mockTopPosts.add(new SocialNetworkPost(1L, now.minusDays(1), "Technology", "Author1", "Top Content 1", 200));
        mockTopPosts.add(new SocialNetworkPost(2L, now.minusDays(1), "Science", "Author2", "Top Content 2", 250));

        when(postRepository.findTopPostsPerCategory("Technology", PageRequest.of(0, 10))).thenReturn(mockTopPosts);

        List<SocialNetworkPost> result = postRepository.findTopPostsPerCategory("Technology", PageRequest.of(0, 10));

        assertEquals(mockTopPosts.size(), result.size());
        assertEquals(mockTopPosts, result);
    }
}
