package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.domain.QPost;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public List<Post> getList(int page) {
        return jpqlQueryFactory.selectFrom(QPost.post)
                .limit(10)
                .offset((long) (page - 1) * 10)
                .fetch();
    }
}
