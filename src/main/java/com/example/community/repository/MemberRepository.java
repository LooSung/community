package com.example.community.repository;

import com.example.community.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdAndAccountType(Long memberId, String accountType);
    Optional<Member> findMemberByNickname(String nickname);
}