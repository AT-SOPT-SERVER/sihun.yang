package org.sopt.assignment.dto.response;

public record ErrorResponse(
        int status,
        String message
) {}