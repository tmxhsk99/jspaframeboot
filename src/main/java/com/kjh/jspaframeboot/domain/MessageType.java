package com.kjh.jspaframeboot.domain;

import lombok.Getter;

@Getter
public enum MessageType {
    JOIN("JOIN"),
    CHAT("CHAT");

    private final String value;

    private MessageType(String value) {
        this.value = value;
    }



}
