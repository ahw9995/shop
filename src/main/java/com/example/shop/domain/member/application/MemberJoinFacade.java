package com.example.shop.domain.member.application;

import com.example.shop.domain.member.domain.Member;
import com.example.shop.domain.member.dto.JwtToken;
import com.example.shop.domain.member.dto.MemberJoinReq;
import com.example.shop.domain.member.dto.MemberJoinRes;
import com.example.shop.domain.member.exception.MemberJoinExistEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberJoinFacade {

    private final MemberJoinService memberJoinService;
    private final MemberMarketingService memberMarketingService;
    private final MemberTokenService memberTokenService;
    private final MemberLoginService memberLoginService;


    @Transactional
    public MemberJoinRes memberJoin(MemberJoinReq req) {

        if (memberLoginService.existEmail(req.getEmail())) {
            throw new MemberJoinExistEmailException("이미 가입된 이메일 주소 입니다.");
        }

        Member member = memberJoinService.memberJoin(req);
        memberMarketingService.saveMemberMarketing(member, req.isMarketingYn());
        JwtToken token = memberTokenService.createToken(member.getSeq(), req.getEmail());
        member.updateRefreshToken(token.getRefreshToken());
        
        return MemberJoinRes.builder()
            .accessToken(token.getAccessToken())
            .refreshToken(token.getRefreshToken())
            .build();

    }

}
