package com.example.shop.domain.member.application;

import com.example.shop.domain.member.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberTokenService {


    public JwtToken createToken(long memberSeq, String email) {

        return JwtToken.builder()
            .accessToken(createAccessToken(memberSeq, email))
            .refreshToken(createRefreshToken(memberSeq, email))
            .build();
    }

    public JwtToken renewalAccessToken(String refreshToken) {

        validToken(refreshToken);
        return JwtToken.builder()
            .accessToken("")
            .refreshToken("")
            .build();
    }

    private String createAccessToken(long memberSeq, String email) {
        return "";
    }

    private String createRefreshToken(long memberSeq, String email) {
        return "";
    }

    private void validToken(String token) {

    }
}
