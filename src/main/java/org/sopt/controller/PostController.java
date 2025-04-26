package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.enums.SuccessCode;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestBody PostRequest request) {
        postService.createPost(request.title());
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK, postService.getAllPosts()));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK, post));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

    @PatchMapping("/post/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@PathVariable Long id, @RequestBody PostRequest request){
        postService.updatePost(id, request.title());
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }
}