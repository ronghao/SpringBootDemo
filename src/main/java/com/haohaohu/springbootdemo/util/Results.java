package com.haohaohu.springbootdemo.util;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Results {

    private static final Logger logger = LoggerFactory.getLogger(Results.class);

    public static final int SUCCESS = 200;

    public static final int FAIL = 500;

    private Results() {
    }

    @Deprecated
    public static <T> Result<T> success(String message) {
        logger.debug("<<<< Result ok:message【{}】", message);
        return new Result<>(SUCCESS, message, null);
    }

    public static <T> Result<T> ok(T data) {
        logger.debug("<<<<Result ok:data【{}】", data);
        return new Result<>(SUCCESS, "success", data);
    }

    public static <T> Result<T> ok(T data, String message) {
        logger.debug("<<<<Result ok:T【{}】data【{}】", data, message);
        return new Result<>(SUCCESS, message, data);
    }

    public static <T> Result<T> ok(String message) {
        logger.debug("<<<<Result ok:message ok【{}】", message);
        return new Result(SUCCESS, message, message);
    }

    public static <T> Result<T> error(Integer code, String message) {
        logger.debug("<<<<Result error:code【{}】,message【{}】", code, message);
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> error(String message) {
        logger.debug("<<<<Result error:message【{}】", message);
        return new Result<>(FAIL, message, null);
    }

    public static <T> Result<T> okAPI(T data) {
        logger.debug("<<<<Result ok:data【{}】", data);
        return new Result<>(SUCCESS, "", data);
    }

    public static <T> Result<T> errorAPI(String message) {
        logger.debug("<<<<Result error:message【{}】", message);
        return new Result<>(FAIL, message, null);
    }

    public static <T> boolean isSuccess(Result<T> result) {
        if (result == null) {
            return false;
        }
        return result.getCode() == Results.SUCCESS;
    }

    public static <T> boolean isFail(Result<T> result) {
        if (result == null) {
            return true;
        }
        return result.getCode() == Results.FAIL;
    }

    public static <T> boolean noSuccess(Result<T> result) {
        return isNotSuccess(result);
    }

    public static <T> boolean isNotSuccess(Result<T> result) {
        if (result == null) {
            return true;
        }
        return result.getCode() != Results.SUCCESS;
    }

    public static <T> Result<T> api(Result<T> result) {
        if (isSuccess(result)) {
            result.setMessage(null);
        }
        return result;
    }

    public static <T> Result<T> result(boolean update, T success, String error) {
        return update ? Results.ok(success) : Results.error(error);
    }

    public static JSONObject jsonError(String error) {
        JSONObject backRet = new JSONObject();
        backRet.put("state", 0);
        backRet.put("errmsg", error);
        return backRet;
    }
}

