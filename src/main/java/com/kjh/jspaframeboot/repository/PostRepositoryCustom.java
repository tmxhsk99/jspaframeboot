package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.Post;
import com.kjh.jspaframeboot.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
