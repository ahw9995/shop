package com.example.shop.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class JwtClaim {

    private final String email;
    private final String seq;

}
