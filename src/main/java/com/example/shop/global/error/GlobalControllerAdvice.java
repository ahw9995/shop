package com.example.shop.global.error;

import com.example.shop.domain.member.exception.JwtTokenExpiredException;
import com.example.shop.domain.member.exception.JwtTokenRenewalFailedException;
import com.example.shop.domain.member.exception.JwtTokenVerifyFailedException;
import com.example.shop.domain.member.exception.MemberJoinExistEmailException;
import com.example.shop.global.common.response.ResponseObject;
import com.example.shop.global.common.response.ResultObject;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject<Object>> methodArgumentNotValidExceptionHandler(
        MethodArgumentNotValidException e) {

        log.error("error => ", e);

        final String errorMessage =
            StringUtils.isNotEmpty(e.getBindingResult().getFieldError().getDefaultMessage())
                ? e.getBindingResult().getFieldError().getDefaultMessage() : "파라미터 검증에 실패하였습니다.";

        return ResponseEntity.internalServerError()
            .body(new ResponseObject<>(
                new ResultObject(errorMessage)));
    }

    @ExceptionHandler(MemberJoinExistEmailException.class)
    public ResponseEntity<ResponseObject<Object>> memberJoinExistEmailExceptionHandler(
        MemberJoinExistEmailException e) {

        log.error("error => ", e);

        return ResponseEntity.internalServerError()
            .body(new ResponseObject<>(new ResultObject(e.getMessage())));
    }

    @ExceptionHandler(JwtTokenExpiredException.class)
    public ResponseEntity<ResponseObject<Object>> jwtTokenExpiredExceptionHandler(
        JwtTokenExpiredException e) {

        log.error("token expired => ", e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ResponseObject<>(
                new ResultObject(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage())));
    }

    @ExceptionHandler(JwtTokenVerifyFailedException.class)
    public ResponseEntity<ResponseObject<Object>> jwtTokenVerifyFailedExceptionHandler(
        JwtTokenVerifyFailedException e) {

        log.error("token valid failed => ", e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ResponseObject<>(
                new ResultObject(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage())));
    }

    @ExceptionHandler(JwtTokenRenewalFailedException.class)
    public ResponseEntity<ResponseObject<Object>> jwtTokenRenewalFailedExceptionHandler(
        JwtTokenRenewalFailedException e) {

        log.error("token renewal failed => ", e);

        return ResponseEntity.internalServerError()
            .body(new ResponseObject<>(new ResultObject(e.getMessage())));
    }
}
