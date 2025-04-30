package org.sopt.dto;

import org.sopt.domain.Post;

public record PostAllResponse(
        String title,
        String userName
) {
    public static PostAllResponse of(Post post){
        return new PostAllResponse(
                post.getTitle(),
                post.getUser().getName());
    }
}
