package com.kjh.jspaframeboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ChatController {

    @GetMapping("/chat/chatRoom")
    public String getDashBoard(Model model){
        log.info("ChatController.chatRoom()");
        model.addAttribute("chatRoomNo", "1");
        return "chat/chatRoom";
    }

}

