package com.lzx.distributed.lock.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

    public static String toJsonStr(Object object) throws JsonProcessingException {
        return  new ObjectMapper().writeValueAsString(object);
    }

}
