package org.sopt.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;


import java.util.Arrays;

public enum PostTag {
    BACKEND,
    DATABASE,
    INFRA;
}
