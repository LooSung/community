package com.example.community.repository;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = " SELECT P.title AS title, " +
                   "        P.content AS content, " +
                   "        M.nickname AS nickname, " +
                   "        CASE WHEN M.account_type = 'Realtor' THEN '공인중개사' " +
                   "             WHEN M.account_type = 'Lessor' THEN '임대인' " +
                   "             WHEN M.account_type = 'Lessee' THEN '임차인' END AS accountType," +
                   "        COALESCE((SELECT COUNT(*) FROM POST_LIKE PL1 WHERE P.id = PL1.post_id AND PL1.member_id = :member_id), 0) AS checkSelfLike, " +
                   "        (SELECT COUNT(*) FROM POST_LIKE PL2 WHERE P.id = PL2.post_id) AS likeCount " +
                   "   FROM POST P " +
                   "  INNER JOIN MEMBER AS M ON P.member_id = M.id " +
                   "  LEFT JOIN POST_LIKE PL ON P.id = PL.post_id AND (PL.member_id = null OR PL.member_id IS NULL)", nativeQuery = true)
    List<Object[]> findByPostList(@Param("member_id") Long memberId);
}