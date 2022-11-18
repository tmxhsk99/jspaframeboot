package com.kjh.jspaframeboot.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //이름명

    private Integer maxChatRoomUser;//최대 인원

    @Builder
    public ChatRoom(String name, Integer maxChatRoomUser) {
        this.name = name;
        this.maxChatRoomUser = maxChatRoomUser;
    }

}
