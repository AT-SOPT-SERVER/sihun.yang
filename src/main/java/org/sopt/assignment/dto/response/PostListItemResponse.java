package org.sopt.assignment.dto.response;

import org.sopt.assignment.domain.Tag;

public record PostListItemResponse(Long contentId, String title, String writer, Tag tag) {}
