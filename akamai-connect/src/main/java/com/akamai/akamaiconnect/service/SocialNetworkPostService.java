package com.akamai.akamaiconnect.service;

import com.akamai.akamaiconnect.model.SocialNetworkPost;
import java.util.List;

public interface SocialNetworkPostService {

    List<SocialNetworkPost> getAllPosts();

    List<SocialNetworkPost> getTopPostsPerCategory(String postcategory, int count);

    List<SocialNetworkPost> searchPostsByAuthor(String author);

}
