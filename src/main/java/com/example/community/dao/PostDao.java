package com.example.community.dao;

import com.example.community.dto.PostDto;
import com.example.community.model.Member;
import com.example.community.model.Post;
import com.example.community.model.PostHistory;
import com.example.community.model.PostLike;
import com.example.community.repository.PostHistoryRepository;
import com.example.community.repository.PostLikeRepository;
import com.example.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDao {

    private final PostRepository postRepository;
    private final PostHistoryRepository postHistoryRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void createPost(PostDto.CreatePost requestDTO, Optional<Member> memberInfo) {
        try {
            Post post = Post.builder()
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .memberId(memberInfo.get().getId())
                    .build();
            postRepository.save(post);

            PostHistory postHistory = PostHistory.builder()
                    .postId(post.getId())
                    .memberId(memberInfo.get().getId())
                    .createdAt(LocalDateTime.now())
                    .build();
            postHistoryRepository.save(postHistory);
        } catch (Exception ex) {
            throw new RuntimeException(ex + " Save Post Failed");
        }
    }

    @Transactional
    public void updatePost(PostDto.UpdatePost requestDTO, Optional<Member> memberInfo) {
        try {
            Post post = Post.builder()
                    .id(requestDTO.getPostId())
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .memberId(memberInfo.get().getId())
                    .build();
            postRepository.save(post);

            PostHistory postHistory = PostHistory.builder()
                    .postId(post.getId())
                    .memberId(memberInfo.get().getId())
                    .updatedAt(LocalDateTime.now())
                    .build();
            postHistoryRepository.save(postHistory);
        } catch (Exception ex) {
            throw new RuntimeException(ex + " Save Post Failed");
        }
    }

    public void deletePost(Optional<Member> memberInfo, Optional<Post> postInfo) {
        try {
            Post post = postInfo.get();
            postRepository.delete(post);

            PostHistory postHistory = PostHistory.builder()
                    .postId(post.getId())
                    .memberId(memberInfo.get().getId())
                    .deletedAt(LocalDateTime.now())
                    .build();
            postHistoryRepository.save(postHistory);
        } catch (Exception ex) {
            throw new RuntimeException(ex + " Delete Post Failed");
        }
    }

    public void likePost(Optional<Member> memberInfo, Optional<Post> postInfo) {
        try {
            PostLike like = PostLike.builder()
                    .postId(postInfo.get().getId())
                    .memberId(memberInfo.get().getId())
                    .likedAt(LocalDateTime.now())
                    .build();
            postLikeRepository.save(like);
        } catch (Exception ex) {
            throw new RuntimeException(ex + " Delete Post Failed");
        }
    }
}
