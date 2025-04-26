package org.sopt.assignment.exception;

public enum ErrorMessage {
    // 게시글 관련
    POST_NOT_FOUND("해당 ID의 게시글이 존재하지 않습니다."),
    DUPLICATE_TITLE("이미 존재하는 제목입니다."),
    SPAM_LIMIT("3분 이내에는 게시글을 다시 작성할 수 없습니다."),
    KEYWORD_NOT_FOUND("해당 키워드의 게시글이 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
