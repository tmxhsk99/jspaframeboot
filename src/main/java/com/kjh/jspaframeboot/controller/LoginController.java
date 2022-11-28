package com.kjh.jspaframeboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    /**
     * 로그인 페이지로 이동한다.
     * @return
     */
    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }


}
