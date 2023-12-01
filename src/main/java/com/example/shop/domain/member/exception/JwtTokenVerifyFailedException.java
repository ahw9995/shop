package com.example.shop.domain.member.exception;

public class JwtTokenVerifyFailedException extends RuntimeException {

    public JwtTokenVerifyFailedException(String message) {
        super(message);
    }
}
