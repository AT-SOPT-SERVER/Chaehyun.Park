package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }

    public boolean checkSameTitle(String title){
        for (Post post : postList) {
            if (Objects.equals(post.getTitle(), title)) {
                return true;
            }
        }

        return false;
    }

    public boolean delete(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }

    public boolean update(int id, String newTitle) {
        Post post = findPostById(id);
        if (post != null) {
            post.setTitle(newTitle);
            return true;
        }
        return false;
    }
}