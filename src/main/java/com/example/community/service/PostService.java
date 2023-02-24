package com.example.community.service;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;
import com.example.community.model.PostHistory;
import com.example.community.model.PostLike;

import java.util.List;

public interface PostService {
    void createPost(PostDto.CreatePost requestDTO, String authentication);
    void updatePost(PostDto.UpdatePost requestDTO, String authentication);
    void deletePost(Long postId, String authentication);
    void likePost(Long postId, String authentication);
    List<PostDto.PostList> getPostList(String authentication);
    List<PostHistory> getPostHistoryList(Long postId);
    List<PostLike> getPostLikeList(Long userId);
}
