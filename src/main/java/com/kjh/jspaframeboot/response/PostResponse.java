package com.kjh.jspaframeboot.response;


import com.kjh.jspaframeboot.domain.Post;
import lombok.Builder;
import lombok.Getter;

/**
 서비스 정책에 맞는 PostResponse
 1. 타이틀을 10글자로 제한된다.
 */
@Getter
@Builder
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0,Math.min(title.length(),10));
        this.content = content;
    }


}
