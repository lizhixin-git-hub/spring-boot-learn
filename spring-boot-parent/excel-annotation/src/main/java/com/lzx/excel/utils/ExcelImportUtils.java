package com.lzx.excel.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Excel导入工具类
 */
@Component
public class ExcelImportUtils {

    /**
     * 从指定位置获取文件后进行导入
     *
     * @param filePath   文件路径
     * @param titleRows  表格标题行数,默认0
     * @param headerRows 表头行数,默认1
     * @param pojoClass  上传后需要转化的对象
     * @return 数据列表
     */
    public <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (Strings.isEmpty(filePath)) {
            return null;
        }

        final ImportParams params = new ImportParams();
        // 表格标题行数,默认0
        params.setTitleRows(titleRows);
        // 表头行数,默认1
        params.setHeadRows(headerRows);
        // 是否需要保存上传的Excel
        params.setNeedSave(true);
        // 保存上传的Excel目录
        params.setSaveUrl("/excel/");
        return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
    }

    /**
     * 上传文件导入
     *
     * @param file       文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param needVerfiy 是否检验excel内容
     * @param pojoClass  导入的对象
     * @return 数据列表
     * @throws Exception List<T>
     */
    public <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, boolean needVerfiy,
                                   Class<T> pojoClass) throws Exception {
        if (file == null) {
            return null;
        }

        return baseImport(file.getInputStream(), titleRows, headerRows, needVerfiy, pojoClass);

    }

    /**
     * 最基础导入
     *
     * @param inputStream 文件输入流
     * @param titleRows   表格标题行数,默认0
     * @param headerRows  表头行数,默认1
     * @param needVerify  是否需要检测excel
     * @param pojoClass   导入的对象
     * @return 数据列表
     * @throws Exception List<T>
     */
    private <T> List<T> baseImport(InputStream inputStream, Integer titleRows, Integer headerRows,
                                   boolean needVerify, Class<T> pojoClass) throws Exception {
        if (inputStream == null) {
            return null;
        }

        final ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setSaveUrl("/excel/");
        params.setNeedSave(true);
        params.setNeedVerify(needVerify);
        return ExcelImportUtil.importExcel(inputStream, pojoClass, params);

    }

}
