package com.example.shop.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class JwtToken {

    private final String accessToken;
    private final String refreshToken;
}
