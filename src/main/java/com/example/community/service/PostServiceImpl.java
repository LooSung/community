package com.example.community.service;

import com.example.community.dto.PostDto;
import com.example.community.model.Member;
import com.example.community.model.Post;
import com.example.community.model.PostHistory;
import com.example.community.model.PostLike;
import com.example.community.repository.MemberRepository;
import com.example.community.repository.PostHistoryRepository;
import com.example.community.repository.PostLikeRepository;
import com.example.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostHistoryRepository postHistoryRepository;
    private final PostLikeRepository postLikeRepository;

    // 글 작성
    @Transactional
    public void createPost(PostDto.CreatePost requestDTO, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if(memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        } else {
            Post post = Post.builder()
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .editor(memberInfo.get())
                    .build();
            postRepository.save(post);

            PostHistory postHistory = PostHistory.builder()
                    .post(post)
                    .editor(memberInfo.get())
                    .createdAt(LocalDateTime.now())
                    .build();
            postHistoryRepository.save(postHistory);
        }
    }

    // 글 수정
    public void updatePost(PostDto.UpdatePost requestDTO, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if(memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        // Check Post Info
        Optional<Post> postInfo = checkPost(requestDTO.getPostId());
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        } else {
            Post post = Post.builder()
                    .title(requestDTO.getTitle())
                    .content(requestDTO.getContent())
                    .build();
            postRepository.save(post);

            PostHistory postHistory = PostHistory.builder()
                    .post(post)
                    .editor(memberInfo.get())
                    .updatedAt(LocalDateTime.now())
                    .build();
            postHistoryRepository.save(postHistory);
        }
    }

    // 글 삭제
    public void deletePost(Long userId, Long postId, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if(memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        // Check Post Info
        Optional<Post> postInfo = checkPost(postId);
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        } else {
            Post post = postInfo.get();
            postRepository.delete(post);

            PostHistory postHistory = PostHistory.builder()
                    .post(post)
                    .editor(memberInfo.get())
                    .deletedAt(LocalDateTime.now())
                    .build();
            postHistoryRepository.save(postHistory);

            Member member = Member.builder()
                    .id(memberInfo.get().getId())
                    .quit(true)
                    .build();
            memberRepository.save(member);
        }
    }

    public void likePost(Long userId, Long postId, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if(memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        // Check Post Info
        Optional<Post> postInfo = checkPost(postId);
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        }

        Optional<PostLike> postLikeInfo = postLikeRepository.findByMemberAndPost(memberInfo.get(), postInfo.get());
        if (postLikeInfo.isPresent()) {
            throw new RuntimeException("이미 좋아요를 누르셨습니다.");
        } else {
            PostLike like = PostLike.builder()
                    .post(postInfo.get())
                    .member(memberInfo.get())
                    .likedAt(LocalDateTime.now())
                    .build();
            postLikeRepository.save(like);
        }
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public List<PostHistory> getPostHistoryList(Long postId) {
        // Check Post Info
        Optional<Post> postInfo = checkPost(postId);
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        }

        return postHistoryRepository.findAllByPostOOrderByCreatedAtDesc(postInfo.get());
    }

    public List<PostLike> getPostLikeList(Long userId) {
        // Check User Info
        Optional<Member> memberInfo = memberRepository.findById(userId);
        if(!memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        return postLikeRepository.findAllByMemberOrderByLikedAtDesc(memberInfo.get());
    }

    private Optional<Member> checkAuth(String authentication) {
        String[] authInfo = authentication.split(" ");
        String accountType = authInfo[0];
        Long memberId = Long.valueOf(authInfo[1]);

        Optional<Member> memberInfo = memberRepository.findByIdAndAccountType(memberId, accountType);
        if(!memberInfo.isPresent()) {
            return null;
        } else {
            return memberInfo;
        }
    }

    private Optional<Post> checkPost(Long postId) {
        Optional<Post> postInfo = postRepository.findById(postId);
        if(!postInfo.isPresent()) {
            return null;
        } else {
            return postInfo;
        }
    }
}
