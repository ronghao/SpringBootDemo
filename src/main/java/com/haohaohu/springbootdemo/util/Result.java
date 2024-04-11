package com.haohaohu.springbootdemo.util;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Deprecated
    public boolean isSuccess() {
        return code == Results.SUCCESS;
    }
}
