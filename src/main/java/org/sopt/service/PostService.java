package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.global.util.IdGenerator;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private static final long MINIMUM_INTERVAL = 180_000;
    private long lastPostCreationTime = 0;

    public boolean createPost(String title) {
        if (checkSameTitle(title)) {
            return false;
        }
        int id = IdGenerator.generateId();
        Post post = new Post(id, title);
        postRepository.save(post);
        lastPostCreationTime = System.currentTimeMillis();
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

    public boolean checkPostTime(long currentTime){
        return lastPostCreationTime == 0 || (currentTime - lastPostCreationTime >= MINIMUM_INTERVAL);
    }

    public boolean checkSameTitle(String title){
        return postRepository.checkSameTitle(title);
    }
}