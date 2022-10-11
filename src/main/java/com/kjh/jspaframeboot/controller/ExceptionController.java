package com.kjh.jspaframeboot.controller;

import com.kjh.jspaframeboot.exception.MyBaseException;
import com.kjh.jspaframeboot.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * 해당 결과로 클라이언트 딴에서 , 처리를 할 수 있다... alert 처리 라던지
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse InvalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            response.addValidation(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return response;
    }

    @ExceptionHandler(MyBaseException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> MyBaseExceptionHandler(MyBaseException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(e.getStatusCode()))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode).body(body);

        return response;
    }

}
