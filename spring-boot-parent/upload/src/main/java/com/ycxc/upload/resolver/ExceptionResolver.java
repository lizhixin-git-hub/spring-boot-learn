package com.ycxc.upload.resolver;

import com.ycxc.upload.common.response.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author Administrator
 */
@RestControllerAdvice
public class ExceptionResolver {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    public R exceptionResolver(Exception e) {
        if(e.getMessage().contains("com.mysql.jdbc") || e.getMessage().contains("java.sql.SQLException") || e.getMessage().contains("org.apache.ibatis.exceptions")){
            return R.error("数据操作异常!"+e.getMessage());
        }
        //未知错误
        return R.error("系统异常："+e.getMessage());
    }

}
