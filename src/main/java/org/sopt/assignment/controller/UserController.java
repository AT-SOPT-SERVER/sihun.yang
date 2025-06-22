package org.sopt.assignment.controller;

import jakarta.validation.Valid;
import org.sopt.assignment.dto.ApiResponse;
import org.sopt.assignment.dto.request.UserCreateRequest;
import org.sopt.assignment.dto.response.UserCreateResponse;
import org.sopt.assignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<UserCreateResponse>> createUser(
            @RequestBody @Valid final UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "회원가입 성공", response));
    }
}
