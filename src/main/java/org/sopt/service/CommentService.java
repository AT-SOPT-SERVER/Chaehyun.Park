package org.sopt.service;

import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.CommentByUserResponse;
import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;
import org.sopt.global.util.CommentValidator;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(
            final CommentRepository commentRepository,
            final UserRepository userRepository,
            final PostRepository postRepository
    ){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void createComment(final String content, final Long userId, final Long postId){
        CommentValidator.validateCommentFormat(content);

        User user = findUserById(userId);
        Post post = findPostById(postId);
        Comment comment = new Comment(content, user, post);
        commentRepository.save(comment);
    }

    public List<CommentByUserResponse> getCommentByUser(final Long userId){
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);
        return comments.stream()
                .map(CommentByUserResponse::of)
                .toList();
    }


    private User findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Post findPostById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }
}
