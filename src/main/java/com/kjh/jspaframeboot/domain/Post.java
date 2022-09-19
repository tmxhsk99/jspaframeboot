package com.kjh.jspaframeboot.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob    //DB 저장시에는 LongText
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
