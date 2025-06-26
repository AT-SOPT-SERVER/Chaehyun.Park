package org.sopt.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private PostTag tag;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY) // 기본값은 EAGER
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentEntities = new ArrayList<>();

    public Post() {

    }

    public Post(String title, String content, PostTag tag, User user) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {return id; }

    public String getTitle() {return title; }

    public String getContent() {return content; }

    public PostTag getTag(){return tag; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getCommentEntities() {
        return commentEntities;
    }


    public void updatePost(String title, String content, PostTag tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }
}