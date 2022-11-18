package com.kjh.jspaframeboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.repository.PostRepository;
import com.kjh.jspaframeboot.request.PostCreate;
import com.kjh.jspaframeboot.request.PostEdit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }


    @Test
    @DisplayName("글 작성시 제목에 '바보'는 포함 될 수 없다. ")
    public void ExceptionTest() throws Exception {
        //given
        PostCreate request = PostCreate.builder()
                .title("kjh 바보")
                .content("content")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/posts/save")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    @DisplayName("글 삭제 테스트")
    void postDelete() throws Exception {
        //given
        Post post = Post.builder()
                .title("kjh")
                .content("content")
                .build();

        postRepository.save(post);

        /**
         * expected
         */
        mockMvc.perform(delete("/posts/{postId}",post.getId()) // PATCH /post/{postId}
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("글 제목 수정")
    void postEdit() throws Exception {
        //given
        Post post = Post.builder()
                .title("kjh")
                .content("content")
                .build();

        postRepository.save(post);


        PostEdit postEdit = PostEdit.builder()
                .title("김주현")
                .content("content")
                .build();

        /**
         * expected
         */
        mockMvc.perform(patch("/posts/{postId}",post.getId()) // PATCH /post/{postId}
                        .contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    @DisplayName("페이지를 0으로 요청하면 첫 페이지를 가져온다.")
    void get_postList() throws Exception {
        //given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("kjh 제목" + i)
                            .content("content" + i)
                            .build();
                })
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        /**
         * expected
         */
        //mockMvc.perform(get("/posts?page=1&sort=id,desc&size=5") //pagable 생성시 , 파라미터로 size를 넘길수도 있지만 application.yml에 기본설정을 지성할 수 도 있다.
        mockMvc.perform(get("/posts?page=0&size=10")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(10)))
                .andExpect(jsonPath("$.[0].title", is("kjh 제목19")))
                .andExpect(jsonPath("$.[0].content", is("content19")))
                .andDo(print());

    }


    
    @Test
    @DisplayName("글 1개 조회")
    void get_post() throws Exception {
        //given
        Post post = Post.builder()
                .title("goo")
                .content("gle")
                .build();
        postRepository.save(post);

        //expected [when + then]
        mockMvc.perform(get("/posts/{postId}",post.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("goo"))
                .andExpect(jsonPath("$.content").value("gle"))
                .andDo(print());


    }
    
    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void sendJson_db_save() throws Exception {
        //given
        PostCreate request = PostCreate
                                .builder()
                                .title("제목입니다.")
                                .content("내용입니다.")
                                .build();

        String jsonString = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/posts/save")
                        .contentType(APPLICATION_JSON)
                        .content(jsonString)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThat(postRepository.count()).isEqualTo(1L);


        Post post = postRepository.findAll().get(0);
        assertThat(post.getTitle()).isEqualTo("제목입니다.");
        assertThat(post.getContent()).isEqualTo("내용입니다.");
    }


    @Test
    @DisplayName("/posts 요청시 ControllerAdvice. 에러 확인 테스트")
    void sendJson_use_controller_advice() throws Exception {

        PostCreate request = PostCreate
                .builder()
                .content("내용입니다.")
                .build();

        String jsonString = objectMapper.writeValueAsString(request);

        //제목을 제거한다.
        mockMvc.perform(post("/posts_use_controller_advice")
                        .contentType(APPLICATION_JSON)
                        .content(jsonString)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 title 값은 필수다.")
    void sendJson_required_title() throws Exception {
        PostCreate request = PostCreate
                .builder()
                .content("내용입니다.")
                .build();

        String jsonString = objectMapper.writeValueAsString(request);
        //제목을 제거한다.
        mockMvc.perform(post("/posts_json")
                        .contentType(APPLICATION_JSON)
                        .content(jsonString)
                )
                .andExpect(status().isOk())
                //.andExpect(content().string("Hello world")) // 결과 내용 검증
                .andExpect(jsonPath("$.title").value("타이틀을 입력해주세요.")) //타이틀 에러시 joson 에러 메시지 검증
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
        PostCreate request = PostCreate
                .builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String jsonString = objectMapper.writeValueAsString(request);
        //expected
        mockMvc.perform(post("/posts_json")
                        .contentType(APPLICATION_JSON)
                        .content(jsonString)
                )
                .andExpect(status().isOk())
                //.andExpect(content().string("Hello world")) //return Type  변경으로 주석처리
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
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .param("title", "글 제목 입니다.")
                        .param("content", "글 내용입니다")
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
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .param("title", "글 제목 입니다.")
                        .param("content", "글 내용입니다")
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
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .param("title", "글 제목 입니다.")
                        .param("content", "글 내용입니다")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts/cookie 로 post 요청")
    void cookieTest() throws Exception {
        //expected
        mockMvc.perform(post("/posts/cookie")
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}