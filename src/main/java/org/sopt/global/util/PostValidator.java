package org.sopt.global.util;

import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;

public class PostValidator {

    private PostValidator() {}

    public static void validateTitleFormat(String title) {
        if (title == null || title.trim().isEmpty()) { //문자열에서 앞뒤의 공백을 제거한 새로운 문자열을 반환
            throw new CustomException(ErrorCode.EMPTY_TITLE);
        }

        int titleLength = title.codePointCount(0, title.length());

        if (titleLength > 30) {
            throw new CustomException(ErrorCode.TITLE_TOO_LONG);
        }
    }
}
