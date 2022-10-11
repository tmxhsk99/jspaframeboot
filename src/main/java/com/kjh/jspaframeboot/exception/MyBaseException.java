package com.kjh.jspaframeboot.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MyBaseException extends RuntimeException{

    public final Map<String,String> validation = new HashMap<>();


    public MyBaseException() {
    }

    public MyBaseException(String message) {
        super(message);
    }

    public MyBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
