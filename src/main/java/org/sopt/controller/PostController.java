package org.sopt.controller;

import org.sopt.dto.PostAllResponse;
import org.sopt.dto.PostRequest;
import org.sopt.dto.PostResponse;
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
    public ResponseEntity<ApiResponse<Void>> createPost(@RequestHeader Long userId, @RequestBody PostRequest request) {
        postService.createPost(request.title(), request.content(), request.tag(), userId);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<List<PostAllResponse>>> getAllPosts() {
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK, postService.getAllPosts()));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@RequestHeader Long userId, @PathVariable Long id) {
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK, postService.getPostById(id, userId)));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@RequestHeader Long userId, @PathVariable Long id){
        postService.deletePost(id, userId);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

    @PatchMapping("/post/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePost(@RequestHeader Long userId, @PathVariable Long id, @RequestBody PostRequest request){
        postService.updatePost(id, request.title(), request.content(), request.tag(), userId);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }
}