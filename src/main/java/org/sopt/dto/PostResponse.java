package org.sopt.dto;


import org.sopt.domain.Post;
import org.sopt.domain.PostTag;

public record PostResponse(
        String title,
        String content,
        PostTag tag,
        String userName
) {
    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getTag(),
                post.getUser().getName()
        );
    }
}
