package org.sopt.controller;

import org.sopt.dto.CommentByUserResponse;
import org.sopt.dto.CommentRequest;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.enums.SuccessCode;
import org.sopt.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/comment/{postId}")
    public ResponseEntity<ApiResponse<Void>> createComment(@RequestHeader Long userId, @PathVariable Long postId, @RequestBody CommentRequest request){
        commentService.createComment(request.content(), userId, postId);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

    @GetMapping("/comments")
    public ResponseEntity<ApiResponse<List<CommentByUserResponse>>> getCommentByUser(@RequestHeader Long userId){
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK, commentService.getCommentByUser(userId)));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@RequestHeader Long userId, @PathVariable Long id){
        commentService.deleteComment(id,userId);
        return ResponseEntity
                .status(SuccessCode.OK.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.OK));
    }

}
