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
    public static class ResponsePost {
        private Long id;
        private String title;
        private String content;
        private String authorId;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
        private LocalDateTime deleteTime;
    }
}