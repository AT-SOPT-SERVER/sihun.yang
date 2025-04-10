package org.sopt.assignment.domain;

public class Post {
    private int id;

    private String title;

    public Post(int id, String title) {
        if (title == null || title.trim().isEmpty()) {

            throw new IllegalArgumentException("제목을 입력해주세요!");
        }
        if(title.length()>30) {
            throw new IllegalArgumentException("제목은 30자 이내를 작성해야합니다!");
        }
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
        this.title = title;
    }
}
