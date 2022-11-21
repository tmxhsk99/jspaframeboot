package com.kjh.jspaframeboot.controller;

import com.kjh.jspaframeboot.request.ChatRoomSearch;
import com.kjh.jspaframeboot.response.ChatRoomResponse;
import com.kjh.jspaframeboot.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final AtomicInteger seq = new AtomicInteger(0);

    //테스트 용
    @GetMapping("/chat/test")
    public String chatRoomTest(Model model){
        log.info("ChatController.chatRoom()");
        model.addAttribute("chatRoomNo", "1");
        return "chat/chatRoomTest";
    }

    @GetMapping("/chat/rooms")
    public String rooms(Model model){
        Long allRoomCount = chatRoomService.getAllRoomCount();
        if(allRoomCount == 0L){//0인경우 기본방 3개를 만들어준다.
            chatRoomService.genDefaultRoom();
        }
        //기본 조건 없는 chatRoom 리스트
        model.addAttribute("rooms", chatRoomService.getList(ChatRoomSearch.builder().build()));
        return "chat/room-list";
    }

    @GetMapping("/chat/rooms/{id}")
    public String room(@PathVariable Long id, Model model) {
        ChatRoomResponse chatRoom = chatRoomService.getChatRoom(id);
        model.addAttribute("room",chatRoom);
        //회원 이름 부여
        model.addAttribute("member","member" + seq.incrementAndGet());
        return "chat/room";
    }


}

