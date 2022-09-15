package com.kjh.jspaframeboot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 title 값은 필수다.")
    void sendJson_required_title() throws Exception {
        //제목을 제거한다.
        mockMvc.perform(post("/posts_json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\",\"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }


    // 글 제목
    // 글 내용
        // 사용자
            // id
            // name
            // level

    // 기존 form 방식의 key value 방식으 애매하다.
    // 그러므로 json 으로 보내야한다.
    @Test
    @DisplayName("/posts_json 로 post 요청 contentType = APPLICATION_JSON , content에 실제 내용 설정")
    void sendJson() throws Exception {
        //expected
        mockMvc.perform(post("/posts_json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\",\"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }


//================================================================================
    // 글 제목
    // 글 내용
    // 예전에는 application/x-www-form-urlencoded 를 많이 사용함
    /**
     * Form Encoded 형태로 전달한다.
     */
    @Test
    @DisplayName("/posts_recv_object 로 post 요청 contentType = APPLICATION_FORM_URLENCODED")
    void simpleControllerRecvObjTest() throws Exception {
        //expected
        mockMvc.perform(post("/posts_recv_object")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title","글 제목 입니다.")
                        .param("content","글 내용입니다")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }
    /**
     * Form Encoded 형태로 전달한다.
     */
    @Test
    @DisplayName("/posts_pramMp 로 post 요청 contentType = APPLICATION_FORM_URLENCODED")
    void simplePostControllerParamMapTest() throws Exception {
        //expected
        mockMvc.perform(post("/posts_pramMap")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title","글 제목 입니다.")
                        .param("content","글 내용입니다")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }


    /**
     * Form Encoded 형태로 전달한다.
     */
    @Test
    @DisplayName("/posts_form_urlencoded 로 post 요청 contentType = APPLICATION_FORM_URLENCODED")
    void simplePostFormUrlEncodedTest() throws Exception {
        //expected
        mockMvc.perform(post("/posts_form_urlencoded")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title","글 제목 입니다.")
                        .param("content","글 내용입니다")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 helle world 를 출력한다")
    void simpleGetTest() throws Exception {
        //expected
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }
}