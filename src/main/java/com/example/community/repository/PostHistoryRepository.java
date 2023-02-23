package com.example.community.repository;

import com.example.community.model.Post;
import com.example.community.model.PostHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostHistoryRepository extends JpaRepository<PostHistory, Long> {
    List<PostHistory> findAllByPostOOrderByCreatedAtDesc(Post post);
}