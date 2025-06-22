package org.sopt.assignment.dto.response;

import org.sopt.assignment.domain.Tag;

public record PostUpdateResponse(
        Long postId,
        String title,
        String content,
        Tag tag
) {}
