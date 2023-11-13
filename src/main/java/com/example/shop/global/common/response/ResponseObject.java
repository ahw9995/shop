package com.example.shop.global.common.response;

public record ResponseObject<T>(T data, ResultObject resultObject) {

    public ResponseObject() {
        this(null, new ResultObject());
    }

    public ResponseObject(T data) {
        this(data, new ResultObject());
    }

    public ResponseObject(ResultObject resultObject) {
        this(null, resultObject);
    }
}
