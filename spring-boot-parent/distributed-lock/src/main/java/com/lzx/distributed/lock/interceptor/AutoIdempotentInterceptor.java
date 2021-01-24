package com.lzx.distributed.lock.interceptor;

import com.lzx.distributed.lock.annotation.AutoIdempotent;
import com.lzx.distributed.lock.service.TokenService;
import com.lzx.distributed.lock.utils.JSONUtil;
import com.lzx.distributed.lock.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 在实际的开发项目中,一个对外暴露的接口往往会面临很多次请求，我们来解释一下幂等的概念：任意多次执行所产生的影响均与一次执行的影响相同。按照这个含义，最终的含义就是 对数据库的影响只能是一次性的，不能重复处理。如何保证其幂等性，通常有以下手段：
 * 1、数据库建立唯一性索引，可以保证最终插入数据库的只有一条数据
 * 2、token机制，每次接口请求前先获取一个token，然后再下次请求的时候在请求的header体中加上这个token，后台进行验证，如果验证通过删除token，下次请求再次判断token
 * 3、悲观锁或者乐观锁，悲观锁可以保证每次for update的时候其他sql无法update数据(在数据库引擎是innodb的时候,select的条件必须是唯一索引,防止锁全表)
 * 4、先查询后判断，首先通过查询数据库是否存在数据，如果存在证明已经请求过了，直接拒绝该请求，如果没有存在，就证明是第一次进来，直接放行。
 * 拦截处理器：主要的功能是拦截扫描到AutoIdempotent到注解到方法,然后调用tokenService的checkToken()方法校验token是否正确，如果捕捉到异常就将异常信息渲染成json返回给前端
 */
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {


    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 预处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被ApiIdempotment标记的扫描
        AutoIdempotent methodAnnotation = method.getAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            try {
                return tokenService.checkToken(request);
                // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            } catch (Exception ex) {
                ResultVo<?> failedResult = ResultVo.getFailedResult(101, ex.getMessage());
                writeReturnJson(response, JSONUtil.toJsonStr(failedResult));
                throw ex;
            }
        }

        //必须返回true,否则会被拦截一切请求
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    /**
     * 返回的json值
     *
     */
    private void writeReturnJson(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
