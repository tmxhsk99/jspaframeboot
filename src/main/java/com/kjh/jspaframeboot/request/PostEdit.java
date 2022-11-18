package com.kjh.jspaframeboot.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
public class PostEdit {
    private Long id;

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    // 이걸 달아주면 검증을해준다 . @RequestBody 옆에 @Valid 선언을 해두면
    @NotBlank(message = "내용를 입력해주세요")
    private String content;

    public PostEdit() {
    }

    public PostEdit(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
