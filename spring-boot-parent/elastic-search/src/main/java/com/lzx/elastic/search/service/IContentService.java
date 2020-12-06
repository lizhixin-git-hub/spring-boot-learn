package com.lzx.elastic.search.service;

import java.util.List;
import java.util.Map;

public interface IContentService {

    Boolean parseContent(String keywords);

    List<Map<String, Object>> searchPage(String keywords, int pageNo, int pageSize);

}
