package org.sopt.assignment.dto.response;

import org.sopt.assignment.domain.Tag;

public record PostUpdateResponse(
        Long contentId,
        String title,
        String content,
        Tag tag
) {}
