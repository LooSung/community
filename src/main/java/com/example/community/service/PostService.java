package com.example.community.service;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;

import java.util.List;

public interface PostService {
    void createPost(PostDto.CreatePost requestDTO, String authentication);
    void updatePost(PostDto.UpdatePost requestDTO, String authentication);
    void deletePost(Long userId, Long postId, String authentication);
    void likePost(Long userId, Long postId, String authentication);
    List<Post> getPostList();


    // List<LikeHistoryDto> getLikeHistory(Long postId);
    // void addWriteHistory(PostDto postDto);
    // void addUpdateHistory(PostDto postDto);
    // void addDeleteHistory(Long postId);
}
