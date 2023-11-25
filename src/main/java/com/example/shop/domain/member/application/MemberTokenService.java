package com.example.shop.domain.member.application;

import com.example.shop.domain.member.dto.JwtClaim;
import com.example.shop.domain.member.dto.JwtToken;
import com.example.shop.domain.member.dto.TokenType;
import com.example.shop.global.properties.JwtTokenProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberTokenService {

    private final JwtTokenProperties jwtTokenProperties;


    public JwtToken createToken(long memberSeq, String email) {

        return JwtToken.builder()
            .accessToken(createAccessToken(memberSeq, email))
            .refreshToken(createRefreshToken(memberSeq, email))
            .build();
    }

    public JwtToken renewalAccessToken(String refreshToken) {

        // TODO: 토큰 검증

        validToken(refreshToken);
        return JwtToken.builder()
            .accessToken("")
            .refreshToken("")
            .build();
    }

    private String createAccessToken(long memberSeq, String email) {
        return createJwtToken(JwtClaim.builder()
                .seq(memberSeq)
                .email(email)
                .build(), TokenType.ACCESS_TOKEN);
    }

    private String createRefreshToken(long memberSeq, String email) {
        return createJwtToken(JwtClaim.builder()
            .seq(memberSeq)
            .email(email)
            .build(), TokenType.REFRESH_TOKEN);
    }

    // TODO: 토큰 검증
    private void validToken(String token) {
    }

    private String createJwtToken(JwtClaim jwtClaim, TokenType tokenType) {
        return this.createJwtToken(jwtClaim, LocalDateTime.now(), tokenType);
    }

    private String createJwtToken(JwtClaim jwtClaim, LocalDateTime localDateTime,
        TokenType tokenType) {

        Date issuedAt = Timestamp.valueOf(localDateTime);

        Date expiredAt = Timestamp.valueOf(localDateTime.plusMinutes(
            TokenType.ACCESS_TOKEN.equals(tokenType)
                ? jwtTokenProperties.getAccessTokenExpiredMinutes()
                : jwtTokenProperties.getRefreshTokenExpiredMinutes()));

        return Jwts.builder()
            .claim("seq", jwtClaim.getSeq())
            .claim("email", jwtClaim.getEmail())
            .issuer("shop")
            .issuedAt(issuedAt)
            .expiration(expiredAt)
            .signWith(Keys.hmacShaKeyFor(
                jwtTokenProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)), SIG.HS512)
            .compact();
    }
}
