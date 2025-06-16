package org.sopt.global.response.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, 40009, "요청 필드가 잘못되었습니다."),

    // 📌 [400][01][01] - 제목 관련
    DUPLICATE_TITLE(HttpStatus.BAD_REQUEST, 40001, "중복된 제목입니다."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, 40002, "제목은 비어 있을 수 없습니다."),
    TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, 40003, "제목은 30자를 초과할 수 없습니다."),

    // 📌 [400][01][04] - 내용 관련
    EMPTY_CONTENT(HttpStatus.BAD_REQUEST, 40004, "내용은 비어 있을 수 없습니다."),
    CONTENT_TOO_LONG(HttpStatus.BAD_REQUEST, 40005, "내용은 1000자를 초과할 수 없습니다."),

    // 📌 [400][01][06] - 이름 관련
    EMPTY_NAME(HttpStatus.BAD_REQUEST, 40006, "이름은 비어 있을 수 없습니다."),
    NAME_TOO_LONG(HttpStatus.BAD_REQUEST, 40007, "이름은 100자를 초과할 수 없습니다."),

    // 📌 [400][02][01] - 게시글 작성 관련
    TOO_EARLY_POST(HttpStatus.BAD_REQUEST, 40008, "마지막 게시글 작성 이후 3분이 지나야 합니다."),

    // 📌 [404][02][01] - 리소스 없음 (도메인: 게시글)
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, 40401, "해당 게시글이 존재하지 않습니다."),

    // 📌 [404][02][02] - 리소스 없음 (도메인: 사용자)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40402, "해당 사용자가 존재하지 않습니다."),

    // 📌 [404][02][03] - 리소스 없음 (도메인: 태그)
    INVALID_TAG(HttpStatus.NOT_FOUND, 40403, "해당 태그가 존재하지 않습니다."),

    // 📌 [500][00][00] - 서버 내부 오류
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "서버 내부 오류입니다.");


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

