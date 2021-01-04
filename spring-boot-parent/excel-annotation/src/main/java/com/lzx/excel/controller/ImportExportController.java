package com.lzx.excel.controller;

import com.lzx.excel.entity.User;
import com.lzx.excel.utils.ExcelExportUtils;
import com.lzx.excel.utils.ExcelImportUtils;
import com.lzx.excel.utils.MsgUtils;
import com.lzx.excel.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel导入导出
 */
@RestController
@RequestMapping("view/ie")
public class ImportExportController {

    private ExcelExportUtils excelExportUtils;

    private ExcelImportUtils excelImportUtils;

    @Autowired
    public void setExcelExportUtils(ExcelExportUtils excelExportUtils) {
        this.excelExportUtils = excelExportUtils;
    }

    @Autowired
    public void setExcelImportUtils(ExcelImportUtils excelImportUtils) {
        this.excelImportUtils = excelImportUtils;
    }

    /**
     * 导出用户信息
     * @author 溪云阁 void
     */
    @GetMapping(value = "/exportExcel")
    public void exportExcel() throws Exception {
        final List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final User user = new User();
            user.setUserId(i);
            user.setSex(1);
            user.setMoney(12332123 + i);
            user.setUserName("小明" + i);
            user.setPrice(23.1f + i);
            user.setNow(new Date());
            userList.add(user);
        }
        excelExportUtils.exportExcel(userList, User.class, "用户信息", "员工信息的sheet", "用户信息表");
    }

    /**
     * 导入用户信息
     * @param file 导入文件
     * @return 导入结果
     * @throws Exception Object
     */
    @GetMapping(value = "/importExcel")
    public ResponseMsg<List<User>> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        final List<User> userList = excelImportUtils.importExcel(file, 1, 1, false, User.class);
        return MsgUtils.buildSuccessMsg(userList);
    }

}
