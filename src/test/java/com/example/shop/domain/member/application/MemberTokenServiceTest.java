package com.example.shop.domain.member.application;

import com.example.shop.domain.member.dto.JwtToken;
import com.example.shop.global.properties.JwtTokenProperties;
import com.example.shop.global.util.encrypt.EncryptSHA;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberTokenServiceTest {

    @Autowired
    MemberTokenService memberTokenService;

    @Autowired
    JwtTokenProperties jwtTokenProperties;

    @Test
    void generateJwtToken() {

        JwtToken jwtToken = memberTokenService.createToken(1, "ahw9995@naver.com");
        System.out.println(jwtToken.toString());
    }

    @Test
    void generateRandomKey() {
        String randomKey = UUID.randomUUID().toString().replace("-", "");
        System.out.println(randomKey);
    }

    @Test
    void generateSecretKey() {
        String key = "5778a579fc114e839ac76867f4737598";

        String encKey = EncryptSHA.encryptSha512(key);
        System.out.println(encKey);

        SecretKey secretKey = Keys.hmacShaKeyFor(encKey.getBytes(StandardCharsets.UTF_8));

    }

    @Test
    void getJwtTokenProperties() {
        System.out.println(jwtTokenProperties.toString());
    }
}