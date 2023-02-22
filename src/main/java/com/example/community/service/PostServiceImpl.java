package com.example.community.service;

import com.example.community.dto.PostDto;
import com.example.community.model.Member;
import com.example.community.model.Post;
import com.example.community.repository.MemberRepository;
import com.example.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 글 작성
    @Transactional
    public PostDto.CreatePost createPost(PostDto.CreatePost requestDTO, String authentication) {
        String[] authInfo = authentication.split(" ");
        String accountType = authInfo[0];
        Long memberId = Long.valueOf(authInfo[1]);

        Optional<Member> memberInfo = memberRepository.findByIdAndAccountType(memberId, accountType);
        if(!memberInfo.isPresent()) {
            throw new RuntimeException("회원에 등록 되지 않은 외부 사용자입니다.");
        }

        Post post = Post.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .editor(memberInfo.get())
                .build();

        PostDto.CreatePost response = new PostDto.CreatePost();
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());

        return response;

    }

    // 글 수정
    /*public void updatePost(Long postId, Post post, String authenticationHeader) {
        Post post = findPostById(postId);
        User user = memberRepository.findByAccountIdAndAccountType(getAccountId(authenticationHeader), getAccountType(authenticationHeader));
        checkPostAuthor(post, user);
        post.update(postDto.getTitle(), postDto.getContent());
    }

    private void checkPostAuthor(Post post, User user) {
        if (!post.getUser().equals(user)) {
            throw new UnauthorizedException("권한이 없는 유저입니다.");
        }
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 존재하지 않습니다."));
    }
    // 글 삭제
    public void deletePost(Long postId, String authenticationHeader) {
        Post post = findPostById(postId);
        User user = memberRepository.findByAccountIdAndAccountType(getAccountId(authenticationHeader), getAccountType(authenticationHeader));
        checkPostAuthor(post, user);
        post.delete();
    }

    public void likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. postId=" + postId));

        if (likeRepository.existsByPostAndUserId(post, userId)) {
            throw new BadRequestException("이미 좋아요를 누르셨습니다.");
        }

        PostLike like = PostLike.builder()
                .post(post)
                .member(userId)
                .build();
        likeRepository.save(like);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }*/
}
