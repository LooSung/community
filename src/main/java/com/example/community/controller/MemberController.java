package com.example.community.controller;

import com.example.community.dto.MemberDto;
import com.example.community.dto.PostDto;
import com.example.community.service.MemberService;
import com.example.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody MemberDto.RequestJoinMember requestDTO) {
        memberService.joinMember(requestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
