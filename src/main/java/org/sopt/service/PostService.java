package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;
import org.sopt.global.util.PostValidator;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Transactional
    public void createPost(final String title) {
        PostValidator.validateTitleFormat(title);
        validateDuplicateTitle(title);
        validatePostTime();

        Post post = new Post(title);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(final Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        postRepository.delete(post);
    }

    @Transactional
    public void updatePost(final Long id, final String title){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        PostValidator.validateTitleFormat(title);
        validateDuplicateTitle(title);

        post.updateTitle(title);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(final Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    private void validateDuplicateTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            throw new CustomException(ErrorCode.DUPLICATE_TITLE);
        }
    }


    private void validatePostTime() {
        postRepository.findTopByOrderByCreatedAtDesc()
                .ifPresent(post -> {
                    if (post.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(3))) {
                        throw new CustomException(ErrorCode.TOO_EARLY_POST);
                    }
                });
    }
}
