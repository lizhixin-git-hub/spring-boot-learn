package com.lzx.file.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lzx.file.utils.LogUtils.logToFile;


/**
 * 测试日志功能
 */
@RestController
@RequestMapping("/Ex")
public class TestExceptionController {
    /**
     * 测试日志切面
     * @return 结果
     */
    @GetMapping("/aspect")
    public int aspect() {
        return 1 / 0;
    }

    /**
     * 测试日志util
     */
    @GetMapping("/util")
    public void util() {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            logToFile(e);
        }
    }

}
