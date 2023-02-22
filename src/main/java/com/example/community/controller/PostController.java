package com.example.community.controller;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;

import com.example.community.service.PostService;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<PostDto.CreatePost> createPost(@RequestBody PostDto.CreatePost requestDTO,
                                                                @RequestHeader HttpHeaders headers) {
        String authentication = headers.getFirst("Authentication");
        PostDto.CreatePost result = postService.createPost(requestDTO, authentication);

        return ResponseEntity.ok(result);
    }

    /*@PutMapping("/{post_id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long post_id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(post_id, post);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long post_id) {
        if (postService.deletePost(post_id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{post_id}/likes")
    public ResponseEntity<Post> likePost(@PathVariable Long post_id) {
        Post likedPost = postService.likePost(post_id);
        if (likedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(likedPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }*/
}
