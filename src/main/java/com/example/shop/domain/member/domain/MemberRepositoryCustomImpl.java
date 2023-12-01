package com.example.shop.domain.member.domain;

import static com.example.shop.domain.member.domain.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findByEmail(String email) {

        return queryFactory.select(member).from(member).where(member.email.eq(email)).fetchOne();
    }

    @Override
    public Member findByRefreshToken(String refreshToken) {
        return queryFactory.select(member).from(member).where(member.refreshToken.eq(refreshToken))
            .fetchOne();
    }
}
