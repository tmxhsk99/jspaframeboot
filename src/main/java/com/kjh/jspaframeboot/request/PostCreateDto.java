package com.kjh.jspaframeboot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostCreateDto {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    // 이걸 달아주면 검증을해준다 . @RequestBody 옆에 @Valid 선언을 해두면
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
