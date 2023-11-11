package com.example.shop.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSearchService {

    // TODO: 이메일 중복 체크
    public void checkExistEmail(String email) {

        // 1. 이메일이 존재하는지 조회한다.

        // 2. 조회 결과에 따른 응답 값을 분리해서 전달한다.

    }

    // TODO: 닉네임 중복 체크
    public void checkExistNickname(String nickname) {

        // 1. 닉네임 중복을 체크한다.

        // 2. 조회 결과에 따른 응답 값을 분리해서 전달한다.
    }

    // TODO: 회원 정보 조회
    public void getMember(long memberSeq) {

        // 1. 회원 번호로 회원 정보를 조회한다.
    }
}
