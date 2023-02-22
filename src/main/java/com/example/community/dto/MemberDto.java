package com.example.community.dto;

import com.example.community.enumDef.PostEnum;
import lombok.Data;

public class MemberDto {
    @Data
    public class RequestCreateMember {
        private Long id;
        private String nickname;
        private PostEnum.AccountType accountType;
        private String accountId;
        private boolean quit;
    }

    @Data
    public class ResponseCreateMember {
        private String nickname;
        private PostEnum.AccountType accountType;
    }
}