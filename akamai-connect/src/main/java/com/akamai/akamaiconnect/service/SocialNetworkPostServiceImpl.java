package com.akamai.akamaiconnect.service;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import com.akamai.akamaiconnect.repository.SocialNetworkPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialNetworkPostServiceImpl implements SocialNetworkPostService {

    private final SocialNetworkPostRepository postRepository;

    @Autowired
    public SocialNetworkPostServiceImpl(SocialNetworkPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<SocialNetworkPost> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<SocialNetworkPost> getTopPostsPerCategory(String postcategory, int count) {
        return postRepository.findTopPostsPerCategory(postcategory, PageRequest.of(0, count));
    }

    @Override
    public List<SocialNetworkPost> searchPostsByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }

    // Other methods as per your requirements
}
