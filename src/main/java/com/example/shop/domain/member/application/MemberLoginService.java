package com.example.shop.domain.member.application;

import com.example.shop.domain.member.domain.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberLoginService {

    private final MemberRepository memberRepository;

    // TODO: 회원 로그인
    public void memberLogin() {

        // 1. 이메일, 패스워드로 회원 정보를 조회한다.

        // 2. 회원 상태를 체크한다.

        // 3. 로그인 기록을 업데이트 한다.

        // 4. 토큰을 발급한다.

        // 5. 토큰을 리턴한다.
    }

    public boolean existEmail(String email) {
        return Objects.nonNull(memberRepository.findByEmail(email));
    }

}
