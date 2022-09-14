package com.kjh.jspaframeboot.controller;

import com.kjh.jspaframeboot.request.PostCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {

    //Sample Get Route
    @GetMapping("/posts")
    public String get() {
        return "Hello world";
    }

    // Http Method
    // GET , POST , PATCH , DELETE , OPTIONS, HEAD ,PUT , TRACE, CONNECT
    // 특징을 알아야한다
    // 글 등록
    // POST Method

    @PostMapping("/posts_form_urlencoded")
    public String post_urlencoded(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hello world";
    }


    /**
     * 위와 동일하지만 매개변수를 Map 으로 받는 경우
     */
    @PostMapping("/posts_pramMap")
    public String posts_pramMap(@RequestParam Map<String,String> params) {
        log.info("params={}",params);
        return "Hello world";
    }

    /**
     * 위와 동일하지만 해당 값에대한 클래스를 만들어 받는 경우
     */
    @PostMapping("/posts_recv_object")
    public String posts_recv_object(PostCreateDto postCreateDto) {
        log.info("params={}",postCreateDto.toString());
        return "Hello world";
    }

    @PostMapping("/posts_json")
    public String posts_json(@RequestBody PostCreateDto params) {
        log.info("prams={}",params.toString());
        return "Hello world";
    }
}
