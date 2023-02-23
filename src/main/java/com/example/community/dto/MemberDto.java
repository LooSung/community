package com.example.community.dto;

import com.example.community.enumDef.PostEnum;
import lombok.Data;

public class MemberDto {
    @Data
    public class RequestJoinMember {
        private String nickname;
        private String password1;
        private String password2;
        private PostEnum.AccountType accountType;
        private String accountId;
        private boolean quit;
    }
}