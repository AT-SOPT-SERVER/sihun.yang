package org.sopt.assignment.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length= 30)
    private String title;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    public Post() {

    }

    public Post(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
