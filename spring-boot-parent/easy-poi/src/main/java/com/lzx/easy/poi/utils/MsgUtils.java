package com.lzx.easy.poi.utils;


import com.lzx.easy.poi.vo.ResponseMsg;

public class MsgUtils {

    /**
     * 构建成功响应
     * @param data 相应数据
     * @param <T> 泛型
     * @return 响应成功结果
     */
    public static <T> ResponseMsg<T> buildSuccessMsg(T data) {
        ResponseMsg<T> responseMsg = new ResponseMsg<>();
        responseMsg.setCode(200);
        responseMsg.setMsg("success");
        responseMsg.setData(data);
        return responseMsg;
    }

}
