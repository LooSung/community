package com.example.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PostDto {
    @Data
    public static class CreatePost {
        private String title;
        private String content;
    }

    @Data
    public static class UpdatePost {
        private Long postId;
        private String title;
        private String content;
    }

    @Data
    public static class PostList {
        private String memberName;
        private String title;
        private String content;
        private String checkSelfLike;
        private int likeCount;
    }
}