package com.example.community.controller;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;

import com.example.community.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity createPost(@RequestBody PostDto.CreatePost requestDTO, @RequestHeader HttpHeaders headers) {
        postService.createPost(requestDTO, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/updatePost")
    public ResponseEntity updatePost(@RequestBody PostDto.UpdatePost requestDTO, @RequestHeader HttpHeaders headers) {
        postService.updatePost(requestDTO, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{post_id}")
    public ResponseEntity deletePost(@PathVariable Long user_id, @PathVariable Long post_id,
                                     @RequestHeader HttpHeaders headers) {
        postService.deletePost(user_id, post_id, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{post_id}/likes")
    public ResponseEntity likePost(@PathVariable Long user_id, @PathVariable Long post_id,
                                   @RequestHeader HttpHeaders headers) {
        postService.likePost(user_id, post_id, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getPostList();
        return ResponseEntity.ok(posts);
    }
}
