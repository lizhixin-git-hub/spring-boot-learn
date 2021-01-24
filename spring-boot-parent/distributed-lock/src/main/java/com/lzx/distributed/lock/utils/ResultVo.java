package com.lzx.distributed.lock.utils;

public class ResultVo<T> {

    private Integer code;

    private String message;

    private T data;

    public static ResultVo<?> getFailedResult(Integer code, String message) {
        ResultVo<?> resultVo = new ResultVo<>();
        resultVo.code = code;
        resultVo.message = message;
        return resultVo;
    }

    public static <T> ResultVo<T> getSuccessResult (T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.code = 0;
        resultVo.message = "操作成功！";
        return resultVo;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
