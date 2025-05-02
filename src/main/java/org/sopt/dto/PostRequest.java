package org.sopt.dto;

import org.sopt.domain.PostTag;

public record PostRequest(String title, String content, PostTag tag) {

}
