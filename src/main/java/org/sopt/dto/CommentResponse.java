package org.sopt.dto;

import org.sopt.domain.Comment;
import org.sopt.domain.Post;

public record CommentResponse(
        String content,
        String userName
) {
    public static CommentResponse of(Comment comment) {
        return new CommentResponse(
                comment.getContent(),
                comment.getUser().getName()
        );
    }
}
