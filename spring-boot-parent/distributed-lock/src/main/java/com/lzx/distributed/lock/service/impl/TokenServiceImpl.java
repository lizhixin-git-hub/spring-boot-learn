package com.lzx.distributed.lock.service.impl;

import com.lzx.distributed.lock.exception.ServiceException;
import com.lzx.distributed.lock.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * token的服务实现类：token引用了redis服务，创建token采用随机算法工具类生成随机uuid字符串,然后放入到redis中(为了防止数据的冗余保留,这里设置过期时间为10000秒,具体可视业务而定)，如果放入成功，最后返回这个token值。checkToken方法就是从header中获取token到值(如果header中拿不到，就从paramter中获取)，如若不存在,直接抛出异常。这个异常信息可以被拦截器捕捉到，然后返回给前端。
 */
@Service
public class TokenServiceImpl implements TokenService {


    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 创建token
     *
     * @return token
     */
    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        try {
            //Constant.Redis.TOKEN_PREFIX
            token.append("TOKEN_PREFIX").append(str);
            redisService.setEx(token.toString(), token.toString(), 10000L);
            boolean notEmpty = !StringUtils.isEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 检验token
     *
     * @param request 请求
     * @return 校验结果
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            // header中不存在token
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                // parameter中也不存在token
                throw new Exception ("parameter中也不存在token");
                //throw new ServiceException(Constant.ResponseCode.ILLEGAL_ARGUMENT, 100);
            }
        }

        if (!redisService.exists(token)) {
            throw new Exception ("请不要重复提交");
            //throw new ServiceException(Constant.ResponseCode.REPETITIVE_OPERATION, 200);
        }

        boolean remove = redisService.remove(token);

        if (!remove) {
            throw new Exception ("请不要重复提交");
            //throw new ServiceException (Constant.ResponseCode.REPETITIVE_OPERATION, 200);
        }

        return true;
    }

}