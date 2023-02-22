package com.example.community.repository;

import com.example.community.model.Post;
import com.example.community.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

}