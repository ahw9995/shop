package com.example.shop.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class MemberJoinReq {

    @NotBlank(message = "이메일 주소는 필수 입력 값 입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private final String password;

    @NotBlank(message = "닉네임은 필수 입력 값 입니다.")
    @Size(min = 2, max = 10)
    private final String nickname;

    private final boolean marketingYn;

}
