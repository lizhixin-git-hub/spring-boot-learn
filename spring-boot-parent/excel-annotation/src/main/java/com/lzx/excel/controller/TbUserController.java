package com.lzx.excel.controller;

import com.lzx.excel.common.core.domain.AjaxResult;
import com.lzx.excel.common.utils.poi.ExcelUtil;
import com.lzx.excel.entity.TbUser;
import com.lzx.excel.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("tbUserController")
@RequestMapping("/tbUser")
public class TbUserController {

    private ITbUserService tbUserService;

    @Autowired
    public void setTbUserService(ITbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    @RequestMapping("/tbUserList")
    public List<TbUser> tbUserList(){
        return tbUserService.list();
    }

    @RequestMapping("/exportTbUser")
    public AjaxResult exportTbUser(){
        List<TbUser> list = tbUserService.list();
        ExcelUtil<TbUser> util = new ExcelUtil<>(TbUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @RequestMapping("/importTemplate")
    public AjaxResult importTemplate(){
        ExcelUtil<TbUser> util = new ExcelUtil<>(TbUser.class);
        return util.importTemplateExcel("用户数据");
    }

    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<TbUser> util = new ExcelUtil<>(TbUser.class);
        List<TbUser> userList = util.importExcel(file.getInputStream());
        tbUserService.saveBatch(userList);
        return AjaxResult.success("导入成功");
    }

}
