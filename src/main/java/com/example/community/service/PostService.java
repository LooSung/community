package com.example.community.service;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;

import java.util.List;

public interface PostService {
    PostDto.CreatePost createPost(PostDto.CreatePost requestDTO, String authentication);
    // void updatePost(PostDto postDto);
    // void deletePost(Long postId);
    // List<PostDto> getPostList();
    // void likePost(Long postId, Long userId);

    // List<LikeHistoryDto> getLikeHistory(Long postId);
    // void addWriteHistory(PostDto postDto);
    // void addUpdateHistory(PostDto postDto);
    // void addDeleteHistory(Long postId);
}
