package com.lzx.elastic.search.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.lzx.elastic.search.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class JsonSerialize {

    private static Logger logger = LoggerFactory.getLogger(JsonSerialize.class);

    public static void main(String[] args) {
        User user = new User();
        user.setNAME("张三");
        //fastJson解决序列化首字母小写问题
        TypeUtils.compatibleWithJavaBean = true;
        System.out.println(JSONObject.toJSONString(user));
    }

    public static <T, E> E post(String path, T obj, Class<E> responseType) {
        E rs = null;
        RestTemplate restTemplate = new RestTemplate();

        //自定义json转换器
        restTemplate.getMessageConverters()
                .stream()
                .filter(MappingJackson2HttpMessageConverter.class::isInstance)
                .map(MappingJackson2HttpMessageConverter.class::cast)
                .findFirst()
                .map(MappingJackson2HttpMessageConverter::getObjectMapper)
                //解决序列化首字母小写问题
                .ifPresent(objectMapper -> {
                    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
                    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
                    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                });

        try {
            logger.info("request Path-> {}", path);
            logger.info("request Param-> {}", obj);

            RequestEntity<T> requestEntity = RequestEntity
                    .post(new URI(path))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(obj);

            logger.info("request json-> {}", JSONObject.toJSONString(obj));
            ResponseEntity<String> resp = restTemplate.exchange(requestEntity, String.class);
            rs = JSON.parseObject(resp.getBody(), responseType);
            logger.info("response Result-> {}", JSONObject.toJSONString(rs));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
