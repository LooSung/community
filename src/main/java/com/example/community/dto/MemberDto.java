package com.example.community.dto;

import com.example.community.model.Member;
import lombok.Data;

public class MemberDto {
    @Data
    public static class RequestJoinMember {
        private String nickname;
        private String password1;
        private String password2;
        private Member.AccountType accountType;
    }
}