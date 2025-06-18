package org.sopt.dto;

import org.sopt.domain.Comment;

public record CommentByUserResponse(
        Long id,
        String content,
        String postTitle
) {
    public static CommentByUserResponse of(Comment comment){
        return new CommentByUserResponse(
                comment.getId(),
                comment.getContent(),
                comment.getPost().getTitle()
        );
    }
}
