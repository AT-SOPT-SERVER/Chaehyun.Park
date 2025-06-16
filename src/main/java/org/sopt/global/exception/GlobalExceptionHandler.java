package org.sopt.global.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 비즈니스 로직에서 발생하는 커스텀 예외
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.fail(errorCode));
    }

    // JSON 파싱 중 발생하는 예외 처리 (Enum 바인딩 실패, 잘못된 필드명 등)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleJsonParseErrors(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();

        // Enum 값 바인딩 실패
        if (cause instanceof InvalidFormatException invalidFormatException) {
            if (invalidFormatException.getTargetType().isEnum()) {
                return ResponseEntity
                        .status(ErrorCode.INVALID_TAG.getHttpStatus())
                        .body(ApiResponse.fail(ErrorCode.INVALID_TAG));
            }
        }

        // DTO에 존재하지 않는 필드 (예: "tg": "BACKEND")
        if (cause instanceof UnrecognizedPropertyException) {
            return ResponseEntity
                    .status(ErrorCode.INVALID_REQUEST.getHttpStatus())
                    .body(ApiResponse.fail(ErrorCode.INVALID_REQUEST));
        }

        // 그 외의 파싱 오류
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_ERROR));
    }

    // 알 수 없는 모든 예외 처리 (최후의 방어선)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnknownException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_ERROR));
    }
}
