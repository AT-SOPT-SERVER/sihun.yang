package org.sopt.assignment.repository;

import org.sopt.assignment.domain.Post;
import org.sopt.assignment.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> searchByKeyword(@Param("keyword") String keyword); // 게시글 제목 기준 검색

    @Query("SELECT p FROM Post p WHERE p.user.nickname LIKE %:nickname%")
    List<Post> searchByWriter(@Param("nickname") String nickname);


    boolean existsByTitle(String title); // 중복 제목 체크

    Post findTopByOrderByCreatedAtDesc(); // 가장 최근 게시글 조회

    List<Post>findAllByOrderByCreatedAtDesc();

    List<Post> findByTag(Tag tag);
}