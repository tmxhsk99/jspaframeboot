package com.kjh.jspaframeboot.service;

import com.kjh.jspaframeboot.controller.repository.PostRepository;
import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.request.PostCreateDto;
import com.kjh.jspaframeboot.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    // 글이 너무 많은 경우 -> 비용이 많이든다.
    // 글이 -> 1억개 -> DB가 뻗음
    // DB -> 애플리케이션 서버를 전달하는 시간  , 트래픽 비용등이 많이 발생할 수 있다.
    // 그러므로 전체 페이지에서 해당 원하는 페이지 값 리턴하도록 변경
    public List<PostResponse> getList(Pageable pageable) {
        // web 에서 1로 날라오면 0으로 바꿔줌 (수동은 안먹힘)  one-indexed-parameters: true
       // Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC,"id"));
        return postRepository.findAll(pageable).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
}
