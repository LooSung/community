package com.example.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingDeque;

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
        private String title;
        private String content;
        private String user;
        private boolean checkSelfLike;
        private Number likeCount;

        public PostList(String  title, String content, String user, boolean checkSelfLike, Number likeCount) {
            this.title = title;
            this.content = content;
            this.user = user;
            this.checkSelfLike = checkSelfLike;
            this.likeCount = likeCount;
        }
    }
}