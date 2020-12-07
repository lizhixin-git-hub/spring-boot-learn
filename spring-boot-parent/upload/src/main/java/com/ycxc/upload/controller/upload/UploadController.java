package com.ycxc.upload.controller.upload;

import com.ycxc.upload.service.upload.ISyncToDcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("uploadController")
@RequestMapping("/upload")
public class UploadController {

    private ISyncToDcService syncToDcService;

    @Autowired
    public void setSyncToDcService(ISyncToDcService syncToDcService) {
        this.syncToDcService = syncToDcService;
    }

    /**
     * 上传维修、检测企业
     */
    @PostMapping("/uploadFixAndInspectEnt")
    public void uploadFixAndInspectEnt(){
        syncToDcService.uploadFixAndInspectEnt();
    }

    /**
     * 上传运输企业
     */
    @PostMapping("/uploadTransportationEnt")
    public void uploadTransportationEnt(){
        syncToDcService.uploadTransportationEnt();
    }

    /**
     *上传检测记录
     */
    @PostMapping("/uploadInspect")
    public void uploadInspect(){
        syncToDcService.uploadInspect();
    }
}
