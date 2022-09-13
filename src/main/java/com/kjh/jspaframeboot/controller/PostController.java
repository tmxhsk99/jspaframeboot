package com.kjh.jspaframeboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    //Sample Get Route
    @GetMapping("/posts")
    public String get(){
        return "Hello world";
    }
}
