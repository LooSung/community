package com.example.community.repository;

import com.example.community.model.Member;
import com.example.community.model.Post;
import com.example.community.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);
    List<PostLike> findAllByMemberIdOrderByLikedAtDesc(Long memberId);
}