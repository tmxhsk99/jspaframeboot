package com.kjh.jspaframeboot.request;

import com.kjh.jspaframeboot.exception.InvalidRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ToString
public class ChatRoomCreate {
    private final Integer MIN_CHATROOM_USER = 2;
    @NotBlank(message = "채팅방 이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "최대 인원설정을 해주세요.")
    private Integer maxChatRoomUser;

    @Builder
    public ChatRoomCreate(String name, Integer maxChatRoomUser) {
        this.name = name;
        this.maxChatRoomUser = maxChatRoomUser;
    }

    public void validate(){
        if(maxChatRoomUser < MIN_CHATROOM_USER){
            throw new InvalidRequest("maxChatRoomUser", "인원설정은 "+MIN_CHATROOM_USER+"명 이상 가능합니다.");
        }
    }
}
