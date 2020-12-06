package com.lzx.elastic.search.controller;

import com.lzx.elastic.search.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/es")
public class ContentController {

    private IContentService contentService;

    @Autowired
    public void setContentService(IContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/parse/{keywords}")
    public Boolean parse(@PathVariable("keywords") String keywords) {
        return contentService.parseContent(keywords);
    }

    @GetMapping("/search/{keywords}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> search(@PathVariable("keywords") String keywords,
                                            @PathVariable("pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) {
        return contentService.searchPage(keywords, pageNo, pageSize);
    }

}
