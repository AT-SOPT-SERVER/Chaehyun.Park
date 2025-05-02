package org.sopt.global.util;

import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;

public class PostValidator {

    private PostValidator() {}

    public static void validateTitleFormat(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_TITLE);
        }

        int titleLength = title.codePointCount(0, title.length());

        if (titleLength > 30) {
            throw new CustomException(ErrorCode.TITLE_TOO_LONG);
        }
    }

    public static void validateContentFormat(String content){
        if (content == null || content.trim().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_CONTENT);
        }

        int contentLength = content.codePointCount(0, content.length());

        if (contentLength > 1000) {
            throw new CustomException(ErrorCode.CONTENT_TOO_LONG);
        }
    }
}
