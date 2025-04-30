package org.sopt.global.response.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    DUPLICATE_TITLE(HttpStatus.BAD_REQUEST, 40001, "중복된 제목입니다."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, 40004, "제목은 비어 있을 수 없습니다."),
    TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, 40005, "제목은 30자를 초과할 수 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, 40401, "해당 게시글이 존재하지 않습니다."),
    TOO_EARLY_POST(HttpStatus.BAD_REQUEST, 40003, "마지막 게시글 작성 이후 3분이 지나야 합니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "서버 내부 오류입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40402, "해당 사용자가 존재하지 않습니다."),
    EMPTY_NAME(HttpStatus.BAD_REQUEST, 40006, "이름은 비어 있을 수 없습니다."),;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}

