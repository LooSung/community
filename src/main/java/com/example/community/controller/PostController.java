package com.example.community.controller;

import com.example.community.dto.PostDto;
import com.example.community.model.Post;

import com.example.community.model.PostHistory;
import com.example.community.model.PostLike;
import com.example.community.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Write Post", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authentication", value = "Account ID", required = true,
                              dataTypeClass = String.class, paramType = "header", defaultValue = " ")
    })
    @PostMapping("/createPost")
    public ResponseEntity createPost(@RequestBody PostDto.CreatePost requestDTO, @RequestHeader HttpHeaders headers) {
        postService.createPost(requestDTO, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @ApiOperation(value = "Modify Post", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authentication", value = "Account ID", required = true,
                              dataTypeClass = String.class, paramType = "header", defaultValue = " ")
    })
    @PutMapping("/updatePost")
    public ResponseEntity updatePost(@RequestBody PostDto.UpdatePost requestDTO, @RequestHeader HttpHeaders headers) {
        postService.updatePost(requestDTO, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(value = "Withdraw Post", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authentication", value = "Account ID", required = true,
                              dataTypeClass = String.class, paramType = "header", defaultValue = " ")
    })
    @DeleteMapping("/deletePost/{post_id}")
    public ResponseEntity deletePost(@PathVariable Long post_id, @RequestHeader HttpHeaders headers) {
        postService.deletePost(post_id, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(value = "Like Post", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authentication", value = "Account ID", required = true,
                              dataTypeClass = String.class, paramType = "header", defaultValue = " ")
    })
    @PostMapping("/likes/{post_id}")
    public ResponseEntity likePost(@PathVariable Long post_id, @RequestHeader HttpHeaders headers) {
        postService.likePost(post_id, headers.getFirst("Authentication"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(value = "View Post List", response = Post.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authentication", value = "Account ID", required = true,
                              dataTypeClass = String.class, paramType = "header", defaultValue = " ")
    })
    @GetMapping("/getPosts")
    public ResponseEntity getPosts(@RequestHeader HttpHeaders headers) {
        List<PostDto.PostList> posts = postService.getPostList(headers.getFirst("Authentication"));
        return ResponseEntity.ok(posts);
    }

    @ApiOperation(value = "View Post History", response = Post.class)
    @GetMapping("/getPostHistory/{post_id}")
    public ResponseEntity<List<PostHistory>> getPostHistory(@PathVariable Long post_id) {
        List<PostHistory> postHistory = postService.getPostHistoryList(post_id);
        return ResponseEntity.ok(postHistory);
    }
    @ApiOperation(value = "View Post Like List By User", response = Post.class)
    @GetMapping("/getPostsLikes/{user_id}")
    public ResponseEntity<List<PostLike>> getPostsLikes(@PathVariable Long user_id) {
        List<PostLike> postLikes = postService.getPostLikeList(user_id);
        return ResponseEntity.ok(postLikes);
    }
}
