package com.kjh.jspaframeboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String,String> InvalidRequestHandler(MethodArgumentNotValidException e) {
        //컨트롤러 매개변수에 RequestResult 가 없어야 Controller Advice로직을 탄다...
        FieldError fieldError = e.getFieldError();
        String field = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();

        HashMap<String, String> response = new HashMap<>();
        response.put(field,defaultMessage);
        return response;
    }
}
