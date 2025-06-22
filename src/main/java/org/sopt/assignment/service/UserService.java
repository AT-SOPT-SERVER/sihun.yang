package org.sopt.assignment.service;

import org.sopt.assignment.domain.User;
import org.sopt.assignment.dto.request.UserCreateRequest;
import org.sopt.assignment.dto.response.UserCreateResponse;
import org.sopt.assignment.exception.ErrorMessage;
import org.sopt.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByNickname(request.nickname())) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NICKNAME.getMessage());
        }

        User user = new User(request.nickname());
        userRepository.save(user);
        return new UserCreateResponse(user.getId());
    }
}
