package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.global.util.IdGenerator;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public boolean createPost(String title) {
        if (postRepository.checkSameTitle(title)) {
            return false;
        }
        int id = IdGenerator.generateId();
        Post post = new Post(id, title);
        postRepository.save(post);
        return true;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public boolean updatePostTitle(int id, String newTitle) {
        return postRepository.update(id, newTitle);
    }
}