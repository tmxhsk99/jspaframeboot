package com.kjh.jspaframeboot.repository;

import com.kjh.jspaframeboot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post,Long> {

}