package com.example.shop.global.common.response;

import lombok.Getter;

public record ResultObject(String code, String message) {

    public ResultObject() {
        this("1", "success");
    }

    public ResultObject(String message) {
        this("00", message);
    }
}
