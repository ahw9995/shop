package com.example.shop.domain.member.api;

import com.example.shop.domain.member.application.MemberJoinFacade;
import com.example.shop.domain.member.dto.MemberJoinReq;
import com.example.shop.domain.member.dto.MemberJoinRes;
import com.example.shop.global.common.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {

    private final MemberJoinFacade memberJoinFacade;


    // 회원 가입
    @PostMapping("/member/join/email")
    public ResponseEntity<ResponseObject<MemberJoinRes>> emailMemberJoin(
        @RequestBody @Valid MemberJoinReq req) {

        return ResponseEntity.ok().body(new ResponseObject<>(memberJoinFacade.memberJoin(req)));
    }

    // 회원 조회

    // 회원 정보 수정

    // 회원 탈퇴

}
