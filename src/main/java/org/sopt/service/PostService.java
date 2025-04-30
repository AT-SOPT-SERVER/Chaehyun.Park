package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.PostAllResponse;
import org.sopt.dto.PostResponse;
import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;
import org.sopt.global.util.PostValidator;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(
            final PostRepository postRepository,
            final UserRepository userRepository
    ){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createPost(final String title, final String content, final Long userId) {
        PostValidator.validateTitleFormat(title);
        PostValidator.validateContentFormat(content);
        validateDuplicateTitle(title);
        validatePostTime(userId);

        User user = findUserById(userId);
        Post post = new Post(title, content, user);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(final Long id, final Long userId){
        Post post = findPostByIdAndUser_Id(id,userId);
        postRepository.delete(post);
    }

    @Transactional
    public void updatePost(final Long id, final String title, final String content, final Long userId){
        Post post = findPostByIdAndUser_Id(id, userId);

        PostValidator.validateTitleFormat(title);
        PostValidator.validateContentFormat(content);
        validateDuplicateTitle(title);

        post.updateTitle(title,content);
    }

    public List<PostAllResponse> getAllPosts() {
        return postRepository.findAllByTitleAndUserName();
    }

    public PostResponse getPostById(final Long id, final Long userId) {
        Post post = findPostByIdAndUser_Id(id, userId);
        return PostResponse.of(post);
    }

    private void validateDuplicateTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            throw new CustomException(ErrorCode.DUPLICATE_TITLE);
        }
    }


    private void validatePostTime(Long userId) {
        postRepository.findTopByUser_IdOrderByCreatedAtDesc(userId) //이거 너무 신기하다 몰랐던 사실
                .ifPresent(post -> {
                    if (post.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(3))) {
                        throw new CustomException(ErrorCode.TOO_EARLY_POST);
                    }
                });
    }

    private Post findPostByIdAndUser_Id(Long id, Long userId) {
        return postRepository.findPostByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    private User findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
