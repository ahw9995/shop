package com.example.shop.domain.member.exception;

public class JwtTokenRenewalFailedException extends RuntimeException {

    public JwtTokenRenewalFailedException(String message) {
        super(message);
    }
}
