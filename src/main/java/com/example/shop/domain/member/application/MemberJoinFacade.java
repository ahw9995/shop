package com.example.shop.domain.member.application;

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


    @Transactional
    public MemberJoinRes memberJoin(MemberJoinReq req) {
        memberJoinService.memberJoin();
        memberMarketingService.saveMemberMarketing(1, req.isMarketingYn());
        JwtToken token = memberTokenService.createToken(1, req.getEmail());

        return MemberJoinRes.builder()
            .accessToken(token.getAccessToken())
            .refreshToken(token.getRefreshToken())
            .build();

    }

}
