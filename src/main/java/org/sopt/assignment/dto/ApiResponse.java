package org.sopt.assignment.dto;

public class ApiResponse<T> {
    private final int status;
    private final String message;
    private final T result;

    public ApiResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
