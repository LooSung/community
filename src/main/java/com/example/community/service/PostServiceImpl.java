package com.example.community.service;

import com.example.community.dao.PostDao;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostHistoryRepository postHistoryRepository;
    private final PostLikeRepository postLikeRepository;

    private final PostDao postDao;

    // 글 작성
    public void createPost(PostDto.CreatePost requestDTO, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if (memberInfo == null) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        } else {
            postDao.createPost(requestDTO, memberInfo);
        }
    }

    // 글 수정
    public void updatePost(PostDto.UpdatePost requestDTO, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if (memberInfo == null) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        // Check Post Info
        Optional<Post> postInfo = checkPost(requestDTO.getPostId());
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        } else {
            postDao.updatePost(requestDTO, memberInfo);
        }
    }

    // 글 삭제
    public void deletePost(Long postId, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if (memberInfo == null) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        // Check Post Info
        Optional<Post> postInfo = checkPost(postId);
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        } else {
            postDao.deletePost(memberInfo, postInfo);
        }
    }

    public void likePost(Long postId, String authentication) {
        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if(memberInfo == null) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        // Check Post Info
        Optional<Post> postInfo = checkPost(postId);
        if(!postInfo.isPresent()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        }

        Optional<PostLike> postLikeInfo = postLikeRepository.findByMemberIdAndPostId(memberInfo.get().getId(),
                                                                                     postInfo.get().getId());
        if (postLikeInfo.isPresent()) {
            throw new RuntimeException("이미 좋아요를 누르셨습니다.");
        } else {
            postDao.likePost(memberInfo, postInfo);
        }
    }

    public List<PostDto.PostList> getPostList(String authentication) {
        Long memberId;

        // Check Authentication
        Optional<Member> memberInfo = checkAuth(authentication);
        if(memberInfo == null) {
            memberId = null;
        } else {
            memberId = memberInfo.get().getId();
        }

        List<Object[]> postLists = postRepository.findByPostList(memberId);

        List<PostDto.PostList> response = new ArrayList<>();
        for (Object[] result : postLists) {
            String title = (String) result[0];

            String content = (String) result[1];

            String user = result[2]+"("+result[3]+")";

            Number checkSelfLike = (Number) result[4];
            boolean selfLike;
            if(checkSelfLike.intValue() == 1) {
                selfLike = true;
            } else {
                selfLike = false;
            }

            Number likeCount = (Number) result[5];

            PostDto.PostList dto = new PostDto.PostList(title, content, user, selfLike, likeCount);
            response.add(dto);
        }

        return response;
    }

    public List<PostHistory> getPostHistoryList(Long postId) {
        // Check Post Info
        List<PostHistory> postHistoryList = postHistoryRepository.findAllByPostIdOrderByCreatedAtDesc(postId);
        if(postHistoryList.isEmpty()) {
            throw new RuntimeException("해당 게시물이 존재하지 않습니다.");
        }

        return postHistoryList;
    }

    public List<PostLike> getPostLikeList(Long userId) {
        // Check User Info
        Optional<Member> memberInfo = memberRepository.findById(userId);
        if(!memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        return postLikeRepository.findAllByMemberIdOrderByLikedAtDesc(memberInfo.get().getId());
    }

    private Optional<Member> checkAuth(String authentication) {
        String[] authInfo = authentication.split(" ");

        try {
            String accountType = authInfo[0];
            Long memberId = Long.valueOf(authInfo[1]);

            Optional<Member> memberInfo = memberRepository.findByIdAndAccountType(memberId, accountType);
            if(!memberInfo.isPresent()) {
                return null;
            } else {
                return memberInfo;
            }
        } catch (Exception ex) {
            throw new RuntimeException("authentication에 해당하지 않습니다.");
        } finally {
            return null;
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
