package com.example.community.repository;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = " SELECT P.title, " +
                   "        P.content, " +
                   "        M.nickname, " +
                   "        M.account_type " +
                   "        COALESCE((SELECT COUNT(*) FROM POST_LIKE PL1 WHERE P.id = PL1.post_id AND PL1.member_id = :memberId), 0) AS checkSelfLike, " +
                   "        (SELECT COUNT(*) FROM POST_LIKE PL2 WHERE P.id = PL2.post_id) AS likeCount " +
                   "   FROM POST P" +
                   "  INNER JOIN MEMBER AS M ON P.member_id = M.id", nativeQuery = true)
    List<PostDto.PostList> findByPostList(@Param("memberId") Long memberId);
}