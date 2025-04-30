package org.sopt.global.util;

import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;

public class UserValidator {

    private UserValidator() {}

    public static void validateNameFormat(String name) {
        if (name == null || name.trim().isEmpty()) { //문자열에서 앞뒤의 공백을 제거한 새로운 문자열을 반환
            throw new CustomException(ErrorCode.EMPTY_NAME);
        }
    }
}
