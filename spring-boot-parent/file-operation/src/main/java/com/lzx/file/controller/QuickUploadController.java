package com.lzx.file.controller;

import com.lzx.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒传
 */
@RestController
@RequestMapping("/QuickUpload")
@CrossOrigin
public class QuickUploadController {

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/")
    public boolean upload(String md5) {
        return fileService.checkMd5(md5);
    }

}
