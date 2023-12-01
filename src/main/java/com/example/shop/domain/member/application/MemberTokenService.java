package com.example.shop.domain.member.application;

import com.example.shop.domain.member.dto.JwtClaim;
import com.example.shop.domain.member.dto.JwtToken;
import com.example.shop.domain.member.dto.TokenType;
import com.example.shop.domain.member.exception.JwtTokenExpiredException;
import com.example.shop.domain.member.exception.JwtTokenRenewalFailedException;
import com.example.shop.domain.member.exception.JwtTokenVerifyFailedException;
import com.example.shop.global.properties.JwtTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.micrometer.common.util.StringUtils;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberTokenService {

    private final JwtTokenProperties jwtTokenProperties;
    private static final String REFRESH_HEADER_KEY = "refresh-token";


    public JwtToken createToken(long memberSeq, String email) {

        String memberSeqString = String.valueOf(memberSeq);

        return JwtToken.builder()
            .accessToken(createAccessToken(memberSeqString, email))
            .refreshToken(createRefreshToken(memberSeqString, email))
            .build();
    }

    public JwtToken renewalAccessToken(HttpHeaders headers) {

        List<String> data = headers.get(REFRESH_HEADER_KEY);

        if (CollectionUtils.isEmpty(data) || StringUtils.isEmpty(data.get(0))) {
            throw new JwtTokenRenewalFailedException("Refresh token 이 존재하지 않습니다.");
        }

        return this.renewalAccessToken(data.get(0));
    }

    public JwtToken renewalAccessToken(String refreshToken) {

        Claims claims = this.validToken(refreshToken);

        String memberSeq = (String) claims.get("seq");
        String email = (String) claims.get("email");

        validToken(refreshToken);
        return JwtToken.builder()
            .accessToken(this.createAccessToken(memberSeq, email))
            .refreshToken(this.createRefreshToken(memberSeq, email))
            .build();
    }

    private String createAccessToken(String memberSeq, String email) {
        return createJwtToken(JwtClaim.builder()
            .seq(memberSeq)
            .email(email)
            .build(), TokenType.ACCESS_TOKEN);
    }

    private String createRefreshToken(String memberSeq, String email) {
        return createJwtToken(JwtClaim.builder()
            .seq(memberSeq)
            .email(email)
            .build(), TokenType.REFRESH_TOKEN);
    }

    private Claims validToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(this.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (SignatureException e) {
            log.error(e.getMessage());
            throw new JwtTokenVerifyFailedException("변조된 토큰입니다.");
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            throw new JwtTokenExpiredException("토큰이 만료되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new JwtTokenVerifyFailedException("토큰 인증 중 오류가 발생하였습니다.");
        }
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
            .signWith(this.getKey(), SIG.HS512)
            .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
            jwtTokenProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }
}
