package org.sopt.global.util;

import org.sopt.global.exception.CustomException;
import org.sopt.global.response.enums.ErrorCode;

public class CommentValidator {

    // 저는 여기에서 의존도 없는데 왜 생성자를 가지고있지? 이런 고민이 갑자기 들었는데
    // private 생성자 ➜ 외부에서 new CommentValidator()로 객체 생성 못함
    // 즉, "이 클래스는 객체로 만들지 말고, 그냥 기능만 써!" 라고 선언하는 방식이라는 걸 한번 더 짚고 넘어가게 되었어요!
    private CommentValidator() {}

    public static void validateCommentFormat(String content){
        if(content == null || content.trim().isEmpty()){
            throw new CustomException(ErrorCode.EMPTY_COMMENT);
        }

        int contentLength = content.codePointCount(0, content.length());

        if(contentLength > 300){
            throw new CustomException(ErrorCode.COMMENT_TOO_LONG);
        }
    }
}
