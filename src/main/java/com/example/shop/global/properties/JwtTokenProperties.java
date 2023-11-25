package com.example.shop.global.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ToString
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt-token")
public class JwtTokenProperties {
    private String secretKey;
    private Integer accessTokenExpiredMinutes;

    private Integer refreshTokenExpiredMinutes;
}
