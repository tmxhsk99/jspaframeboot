package com.kjh.jspaframeboot.controller;

import com.kjh.jspaframeboot.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/join")
    public void join(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 입장하셨습니다");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);

    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        log.info("ChatMessageController.message call!");
        log.info("ChatMessage : " + message);
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(),message);
    }

    /**
     * 외부서버에서 Post방식으로 메시지를 형식에 밪게 보내면 외부에서도 채팅이 가능하다...
     * @param message
     */
    @ResponseBody
    @PostMapping("/chat/external/client/server")
    public void realTimeWebSocketTestRoute(@RequestBody ChatMessage message){
        log.info("External Server ClientData Call!");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(),message);
    }

}
