package org.sopt.dto;


import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.PostTag;

import java.util.List;

public record PostResponse(
        String title,
        String content,
        PostTag tag,
        String userName,
        List<CommentResponse> comments
) {
    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getTag(),
                post.getUser().getName(),
                post.getCommentEntities().stream()
                        .map(CommentResponse::of)
                        .toList()
        );
    }
}
