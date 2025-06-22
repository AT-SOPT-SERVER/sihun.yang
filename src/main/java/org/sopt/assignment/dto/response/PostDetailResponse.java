package org.sopt.assignment.dto.response;

import org.sopt.assignment.domain.Tag;

public record PostDetailResponse(Long postId, String title, String content, String writer, Tag tag) {}


