package com.kjh.jspaframeboot.service;

import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.controller.repository.PostRepository;
import com.kjh.jspaframeboot.request.PostCreateDto;
import com.kjh.jspaframeboot.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;


    @BeforeEach
    void setting() {
        postRepository.deleteAll();
    }


    @Test
    @DisplayName("글 여러개 조회")
    void get_postList() throws Exception {
        //given
        Post post = Post.builder()
                .title("goo1")
                .content("gle1")
                .build();
        postRepository.save(post);

        Post post2 = Post.builder()
                .title("goo2")
                .content("gle2")
                .build();
        postRepository.save(post2);

        //when
        List<PostResponse> posts = postService.getList();

        //then
        assertThat(2L).isEqualTo(posts.size());


    }
    @Test
    @DisplayName("글 1개 조회")
    void read_one_post() {
        //given
        Post post = Post.builder()
                .title("123456789012345")
                .content("gle")
                .build();
        postRepository.save(post);

        //when
        PostResponse returnPost = postService.get(post.getId());

        //then
        assertThat(returnPost).isNotNull();
        assertThat(returnPost.getTitle()).isEqualTo("1234567890");
        assertThat(returnPost.getContent()).isEqualTo("gle");
    }

    @Test
    @DisplayName("PostService 글 작성 테스트")
    void post_save_test() {
        //given
        PostCreateDto post = PostCreateDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(post);

        //then
        assertThat(postRepository.count()).isEqualTo(1L);
        assertThat(post.getTitle()).isEqualTo("제목입니다.");
        assertThat(post.getContent()).isEqualTo("내용입니다.");

    }

}