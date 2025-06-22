package org.sopt.assignment.dto.response;

import org.sopt.assignment.domain.Tag;

public record PostSearchItemResponse(Long postId, String title, String writer, Tag tag) {}
