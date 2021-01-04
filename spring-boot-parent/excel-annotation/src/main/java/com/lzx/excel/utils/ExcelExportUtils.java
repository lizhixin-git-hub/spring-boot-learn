package com.lzx.excel.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.lzx.excel.enums.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 */
@Component
public class ExcelExportUtils {

    private HttpServletResponse response;

    @Autowired(required = false)
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 导出excel
     *
     * @param list           泛型数据
     * @param title          标题
     * @param sheetName      sheet的名称
     * @param pojoClass      需要导出的对象
     * @param fileName       文件名称
     * @param isCreateHeader 是否创建表头
     * @throws IOException void
     */
    public void exportExcel(List<?> list, Class<?> pojoClass, String title, String sheetName, String fileName,
                            boolean isCreateHeader) throws IOException {
        final ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        exportParams.setCreateHeadRows(isCreateHeader);
        baseExport(list, pojoClass, fileName, exportParams);
    }

    /**
     * 导出excel
     *
     * @param list      泛型数据
     * @param title     标题
     * @param sheetName sheet的名称
     * @param pojoClass 需要导出的对象
     * @param fileName  文件名称
     * @throws IOException void
     */
    public void exportExcel(List<?> list, Class<?> pojoClass, String title, String sheetName, String fileName)
            throws IOException {
        baseExport(list, pojoClass, fileName, new ExportParams(title, sheetName, ExcelType.XSSF));
    }

    /**
     * 导出excel
     *
     * @param list         泛型数据
     * @param pojoClass    需要导出的对象
     * @param fileName     文件名称
     * @param exportParams 文件书香
     * @throws IOException void
     */
    public void exportExcel(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams)
            throws IOException {
        baseExport(list, pojoClass, fileName, exportParams);
    }

    /**
     * 多个sheet导出
     *
     * @param list     数据列表
     * @param fileName 文件名
     * @throws IOException void
     */
    public void exportExcel(List<Map<String, Object>> list, String fileName) throws IOException {
        baseExport(list, fileName);
    }

    /**
     * 最基础的对象导出
     *
     * @param list         数据列表
     * @param pojoClass    导出对象
     * @param fileName     文件名称
     * @param exportParams 导出文件属性
     * @throws IOException void
     */
    private void baseExport(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams)
            throws IOException {
        final Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        downLoadExcel(fileName, workbook);
    }

    /**
     * 最基础的多sheet导出
     *
     * @param list     多个不同数据对象的列表
     * @param fileName 文件名称
     * @throws IOException void
     */
    private void baseExport(List<Map<String, Object>> list, String fileName) throws IOException {
        final Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        downLoadExcel(fileName, workbook);
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     * @param workbook excel对象
     * @throws IOException void
     */
    private void downLoadExcel(String fileName, Workbook workbook) throws IOException {
        ServletOutputStream output = null;
        try {
            final String downloadName = URLEncoder.encode(fileName + "." + ExcelTypeEnum.XLSX.getValue(), "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
            output = response.getOutputStream();
            workbook.write(output);
        } catch (final Exception e) {
            throw new IOException(e.getMessage());
        } finally {
            if (output != null) {
                output.flush();
                output.close();
            }
        }
    }

}
