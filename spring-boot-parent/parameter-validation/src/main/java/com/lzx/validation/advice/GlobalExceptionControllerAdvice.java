package com.lzx.validation.advice;

import com.lzx.validation.utils.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.lzx.validation.controller")
public class GlobalExceptionControllerAdvice {

    /**
     * 异常的统一处理，避免每个controller方法上添加BindingResult bindingResult
     * <p>
     * 参数校验不通过时，会抛出 BingBindException 异常，可以在统一异常处理中，做统一处理，这样就不用在每个需要参数校验的地方都用 BindingResult 获取校验结果了
     *
     * @param e 异常
     * @return 校验错误信息
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Result<Map<String, String>> handleValidationException(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        }
        Map<String, String> errorMap = new HashMap<>(16);
        assert bindingResult != null;
        bindingResult.getFieldErrors().forEach((fieldError) ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return new Result<>(Boolean.FALSE, 400, "非法参数 !", errorMap);
    }

}
