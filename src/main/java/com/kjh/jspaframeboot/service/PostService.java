package com.kjh.jspaframeboot.service;

import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.controller.repository.PostRepository;
import com.kjh.jspaframeboot.request.PostCreateDto;
import com.kjh.jspaframeboot.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;



    public Long write(PostCreateDto postCreateDto){
        // postCreate -> Entity
        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .build();
        //클라이언트 측에서 데이터관리가 잘 안될 경우는 다시 데이터를 돌려달라고 하는 경우 도있다...
        return postRepository.save(post).getId();
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        PostResponse response = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        /**
         * 서비스의 크기가 커지면
         * controller -> WebService -(내부적 파싱 )> Repository
         *               PostService (뭔가 외부와연동)
         */

        return response;
    }


    public List<PostResponse> getList() {
        return postRepository.findAll().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
}
