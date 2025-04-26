package org.sopt.assignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.")
        @Size(max = 30, message = "제목은 30자 이하여야 합니다.")
        String title
) {}