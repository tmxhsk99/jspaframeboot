package com.kjh.jspaframeboot.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

/**
 * 에러 응답처리를 위한 클래스
 * {
 *     "code" : "400"
 *     "message" : "잘못된 요청입니다."
 *     "validation" : {
 *         "title" : "값을 입력해 주세요"
 *     }
 * }
 */
@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final HashMap<String,String> validation = new HashMap<>();

    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String field, String defaultMessage) {
        validation.put(field,defaultMessage);
    }
}
