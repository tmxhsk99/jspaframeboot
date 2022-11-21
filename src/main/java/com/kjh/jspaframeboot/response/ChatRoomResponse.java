package com.kjh.jspaframeboot.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.jspaframeboot.domain.ChatMessage;
import com.kjh.jspaframeboot.domain.ChatRoom;
import com.kjh.jspaframeboot.domain.MessageType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoomResponse {
    private final Long id;
    private final String name;
    private final Integer maxChatRoomUser;

    private Set<WebSocketSession> sessions = new HashSet<>();
    public ChatRoomResponse(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.maxChatRoomUser = chatRoom.getMaxChatRoomUser();
    }
    @Builder
    public ChatRoomResponse(Long id, String name, Integer maxChatRoomUser) {
        this.id = id;
        this.name = name;
        this.maxChatRoomUser = maxChatRoomUser;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper) {
        //최초 입장 처리
        if (chatMessage.getType() == MessageType.JOIN) {
            join(session);
            chatMessage.setMessage(chatMessage.getWriter() + "님이 입장했습니다.");
        }
        //메시지 처리
        send(chatMessage, objectMapper);

    }

    private void join(WebSocketSession session) {
        sessions.add(session);
    }

    private <T> void send(T messageObject, ObjectMapper objectMapper) throws RuntimeException{
        sessions.parallelStream().forEach(session -> {
            try {
                TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
                session.sendMessage(message);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    
}

