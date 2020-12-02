package com.atguigu.spring.boot.scheduling.util;

public class OperationResUtils {

    private String code;
    private String msgtype = "000";
    private String msg = "操作成功";


    public static OperationResUtils success() {
        return new OperationResUtils();
    }

    public static OperationResUtils fail(String msg) {
        OperationResUtils operationResUtils = new OperationResUtils();
        operationResUtils.msgtype = "500";
        operationResUtils.msg = msg;
        return operationResUtils;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
