package com.example.community.service;

import com.example.community.dto.MemberDto;
import com.example.community.dto.PostDto;
import com.example.community.model.Member;
import com.example.community.model.Post;
import com.example.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(MemberDto.RequestJoinMember requestDTO) {
        Optional<Member> memberInfo = memberRepository.findMemberByNickname(requestDTO.getNickname());
        if(memberInfo == null) {
            throw new RuntimeException("이미 등록 된 회원입니다.");
        }

        Member joinMember = Member.builder()
                .password(requestDTO.getPassword1())
                .nickname(requestDTO.getNickname())
                .accountId(requestDTO.getAccountType() + " " + 1)
                .accountType(requestDTO.getAccountType())
                .quit(false)
                .build();
        memberRepository.save(joinMember);
    }
}
