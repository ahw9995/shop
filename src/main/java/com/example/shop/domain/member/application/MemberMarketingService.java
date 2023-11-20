package com.example.shop.domain.member.application;

import com.example.shop.domain.member.domain.Member;
import com.example.shop.domain.member.domain.MemberMarketing;
import com.example.shop.domain.member.domain.MemberMarketingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberMarketingService {

    private final MemberMarketingRepository memberMarketingRepository;


    @Transactional
    public void saveMemberMarketing(Member member, boolean isMarketingYn) {

        memberMarketingRepository.save(MemberMarketing.builder()
            .member(member)
            .marketingYn(isMarketingYn)
            .build());
    }
}
