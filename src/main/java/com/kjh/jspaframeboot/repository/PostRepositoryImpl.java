package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.domain.QPost;
import com.kjh.jspaframeboot.request.PostSearch;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpqlQueryFactory.selectFrom(QPost.post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}
