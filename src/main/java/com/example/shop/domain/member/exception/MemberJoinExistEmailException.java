package com.example.shop.domain.member.exception;

public class MemberJoinExistEmailException extends RuntimeException {

    public MemberJoinExistEmailException(String message) {
        super(message);
    }
}
