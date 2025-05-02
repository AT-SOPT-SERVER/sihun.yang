package org.sopt.assignment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
        @Size(max = 20, message = "닉네임은 20자 이하여야 합니다.")
        String nickname
) {}