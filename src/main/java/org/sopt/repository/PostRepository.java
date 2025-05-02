package org.sopt.repository;

import org.sopt.domain.Post;
import org.sopt.dto.PostAllResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);
    Optional<Post> findTopByUser_IdOrderByCreatedAtDesc(Long userId); // userId가 아니라 user.id 라고 써야 Spring이 연관 객체에서 id를 찾을 수 있음
    @Query("SELECT new org.sopt.dto.PostAllResponse(p.title, p.user.name) FROM Post p ORDER BY p.createdAt DESC")
    List<PostAllResponse> findAllByTitleAndUserName();
    Optional<Post> findPostByIdAndUser_Id(Long id, Long userId);
}