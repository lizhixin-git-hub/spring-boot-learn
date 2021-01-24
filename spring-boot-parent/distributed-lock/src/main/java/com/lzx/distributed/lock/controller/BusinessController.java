package com.lzx.distributed.lock.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lzx.distributed.lock.annotation.AutoIdempotent;
import com.lzx.distributed.lock.service.TokenService;
import com.lzx.distributed.lock.utils.JSONUtil;
import com.lzx.distributed.lock.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试用例：
 * 模拟业务请求类，首先我们需要通过/get/token路径通过getToken()方法去获取具体的token，然后我们调用testIdempotence方法，这个方法上面注解了@AutoIdempotent，拦截器会拦截所有的请求，当判断到处理的方法上面有该注解的时候，就会调用TokenService中的checkToken()方法，如果捕获到异常会将异常抛出调用者，下面我们来模拟请求一下：
 */
@RestController
public class BusinessController {

    private TokenService tokenService;

    //private TestService testService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

  /*  @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }*/

    @PostMapping("/get/token")
    public String getToken() throws JsonProcessingException {
        String token = tokenService.createToken();
        if (StringUtils.isNotBlank(token)) {
            ResultVo<String> resultVo = new ResultVo<>();
            resultVo.setCode(0);
            resultVo.setMessage("操作成功");
            resultVo.setData(token);
            return JSONUtil.toJsonStr(resultVo);
        }
        return StringUtils.EMPTY;
    }


    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() throws JsonProcessingException {
        String businessResult = StringUtils.EMPTY;//业务代码testService.testIdempotence();
        if (StringUtils.isNotBlank(businessResult)) {
            ResultVo<String> successResult = ResultVo.getSuccessResult(businessResult);
            return JSONUtil.toJsonStr(successResult);
        }
        return StringUtils.EMPTY;
    }

}