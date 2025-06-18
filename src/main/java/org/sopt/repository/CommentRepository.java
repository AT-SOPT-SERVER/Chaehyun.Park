package org.sopt.repository;

import org.sopt.domain.Comment;
import org.sopt.dto.CommentByUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser_Id(Long userId);
}
