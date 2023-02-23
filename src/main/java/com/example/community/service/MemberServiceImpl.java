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

        if(memberInfo.isPresent()) {
            throw new RuntimeException("이미 등록 된 회원입니다.");
        }

        if(!requestDTO.getPassword1().equals(requestDTO.getPassword2())) {
            throw new RuntimeException("입력 하신 Password가 서로 다릅니다.");
        }

        Long lastId = memberRepository.findFirstByOrderByIdDesc().getId();
        lastId++;

        Member member = Member.builder()
                .password(requestDTO.getPassword1())
                .nickname(requestDTO.getNickname())
                .accountType(requestDTO.getAccountType())
                .accountId(requestDTO.getAccountType() + " " + lastId)
                .quit(false)
                .build();
        memberRepository.save(member);
    }
}
