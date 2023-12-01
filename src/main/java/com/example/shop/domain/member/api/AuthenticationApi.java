package com.example.shop.domain.member.api;

import com.example.shop.domain.member.application.MemberTokenService;
import com.example.shop.domain.member.dto.JwtToken;
import com.example.shop.global.common.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationApi {

    private final MemberTokenService memberTokenService;


    @PostMapping("/token/renewal")
    public ResponseEntity<ResponseObject<JwtToken>> emailMemberJoin(
        @RequestHeader HttpHeaders header) {

        return ResponseEntity.ok()
            .body(new ResponseObject<>(memberTokenService.renewalAccessToken(header)));
    }
}
