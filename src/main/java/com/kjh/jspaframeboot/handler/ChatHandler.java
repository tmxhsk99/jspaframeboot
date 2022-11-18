package com.kjh.jspaframeboot.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.jspaframeboot.domain.ChatMessage;
import com.kjh.jspaframeboot.response.ChatRoomResponse;
import com.kjh.jspaframeboot.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Profile("!stomp")
@Component
public class ChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatRoomService chatRoomService;

    public ChatHandler(ObjectMapper objectMapper, ChatRoomService chatRoomService) {
        this.objectMapper = objectMapper;
        this.chatRoomService = chatRoomService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : {}", payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoomResponse chatRoom = chatRoomService.getChatRoom(Long.valueOf(chatMessage.getChatRoomId()));
        chatRoom.handleMessage(session, chatMessage, objectMapper);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
