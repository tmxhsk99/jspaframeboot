package com.kjh.jspaframeboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DashBoardController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/dashboard/maindata")
    public void mainDataReceiver(){

    }

}
