package com.example.shop.domain.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberMarketingService {


    @Transactional
    public void saveMemberMarketing(long memberSeq, boolean marketingYn) {


    }
}
