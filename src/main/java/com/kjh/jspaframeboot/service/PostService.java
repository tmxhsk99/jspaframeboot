package com.kjh.jspaframeboot.service;

import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.repository.PostRepository;
import com.kjh.jspaframeboot.request.PostCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
