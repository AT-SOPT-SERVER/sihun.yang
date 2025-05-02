package org.sopt.assignment.repository;

import org.sopt.assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);
}