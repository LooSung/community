package com.example.community.repository;

import com.example.community.model.Post;
import com.example.community.model.PostHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHistoryRepository extends JpaRepository<PostHistory, Long> {

}