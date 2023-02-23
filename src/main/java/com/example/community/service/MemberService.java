package com.example.community.service;

import com.example.community.dto.MemberDto;

public interface MemberService {
    void joinMember(MemberDto.RequestJoinMember requestDTO);
}
