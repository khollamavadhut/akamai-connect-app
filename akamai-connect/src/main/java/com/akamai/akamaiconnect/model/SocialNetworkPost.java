package com.akamai.akamaiconnect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "socialnetworkpost")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetworkPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime postdate;
    private String postcategory;
    private String author;
    private String content;
    private Integer viewcount;

    public SocialNetworkPost(LocalDateTime postdate, String postcategory, String author, String content, Integer viewcount) {
        this.postdate = postdate;
        this.postcategory = postcategory;
        this.author = author;
        this.content = content;
        this.viewcount = viewcount;
    }
}
