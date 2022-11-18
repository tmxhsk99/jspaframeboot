package com.kjh.jspaframeboot.exception;

public class ChatRoomNotFound extends MyBaseException{
    private static final String MESSAGE = "존재하지 않는 채팅방 입니다.";

    public ChatRoomNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
