package com.example.shop.global.error;

import com.example.shop.global.common.response.ResponseObject;
import com.example.shop.global.common.response.ResultObject;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
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
}
