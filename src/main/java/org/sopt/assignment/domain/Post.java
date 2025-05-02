package org.sopt.assignment.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.sopt.assignment.domain.Tag;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length= 30)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Post() {

    }

    public Post(String title,String content,User user, Tag tag) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.tag = tag;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public User getUser()
    {
        return user;
    }

    public Tag getTag()
    {
        return tag;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void update(String newTitle,String newContent, Tag newTag) {
        this.title = newTitle;
        this.content = newContent;
        this.tag = newTag;
    }
}
