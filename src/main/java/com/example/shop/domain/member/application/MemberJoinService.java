package com.example.shop.domain.member.application;

import com.example.shop.domain.member.domain.Member;
import com.example.shop.domain.member.domain.MemberRepository;
import com.example.shop.domain.member.dto.MemberJoinReq;
import com.example.shop.global.util.encrypt.EncryptSHA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberJoinService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member memberJoin(MemberJoinReq req) {

        // 회원 정보를 저장한다.
        return memberRepository.save(Member.builder()
            .email(req.getEmail())
            .nickname(req.getNickname())
            .password(EncryptSHA.encryptSha512(req.getPassword()))
            .build());
    }
}
