package com.kjh.jspaframeboot.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ToString
public class PostCreateDto {
    private Long id;

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    // 이걸 달아주면 검증을해준다 . @RequestBody 옆에 @Valid 선언을 해두면
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    public PostCreateDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
