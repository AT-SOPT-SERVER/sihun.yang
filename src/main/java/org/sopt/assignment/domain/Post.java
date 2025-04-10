package org.sopt.assignment.domain;

public class Post {
    private int id;

    private String title;

    public Post(int id, String title) {
        validateTitle(title);
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요!");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이내로 작성해야 합니다!");
        }
    }

}
