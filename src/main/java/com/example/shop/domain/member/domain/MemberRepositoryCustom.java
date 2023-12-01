package com.example.shop.domain.member.domain;

public interface MemberRepositoryCustom {

    Member findByEmail(String email);

    Member findByRefreshToken(String refreshToken);
}
