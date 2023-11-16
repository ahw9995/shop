package com.example.shop.domain.member.application;

import com.example.shop.domain.member.domain.Member;
import com.example.shop.domain.member.dto.JwtToken;
import com.example.shop.domain.member.dto.MemberJoinReq;
import com.example.shop.domain.member.dto.MemberJoinRes;
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
            // 에러 던지기
        }

        Member member = memberJoinService.memberJoin(req);
        memberMarketingService.saveMemberMarketing(member.getSeq(), req.isMarketingYn());
        JwtToken token = memberTokenService.createToken(member.getSeq(), req.getEmail());

        return MemberJoinRes.builder()
            .accessToken(token.getAccessToken())
            .refreshToken(token.getRefreshToken())
            .build();

    }

}
