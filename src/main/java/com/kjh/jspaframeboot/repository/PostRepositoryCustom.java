package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(int page);
}
