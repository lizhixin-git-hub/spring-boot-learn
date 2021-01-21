package com.lzx.excel.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.lzx.excel.entity.HutoolUser;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hutool实现
 * excel导入导出
 */
@RestController
@RequestMapping("/hutool")
public class HutoolController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        List<HutoolUser> list = new ArrayList<>();
        list.add(new HutoolUser("zhangsan", "1231", new Date()));
        list.add(new HutoolUser("zhangsan1", "1232", new Date()));
        list.add(new HutoolUser("zhangsan2", "1233", new Date()));
        list.add(new HutoolUser("zhangsan3", "1234", new Date()));
        list.add(new HutoolUser("zhangsan4", "1235", new Date()));
        list.add(new HutoolUser("zhangsan5", "1236", DateUtil.date(new Date())));
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("birthDay", "生日");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(NumberUtils.INTEGER_TWO, "申请人员信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, Boolean.TRUE);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String fileName = "申请学院";
        String name = toUtf8String(fileName);
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, Boolean.TRUE);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 转换UTF-8
     *
     * @param string 字符串
     * @return UTF-8编码
     */
    private static String toUtf8String(String string) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c <= 255) {
                stringBuffer.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int value : b) {
                    int k = value;
                    if (k < 0) k += 256;
                    stringBuffer.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return stringBuffer.toString();
    }

}
