package com.lzx.elastic.search.service;

import com.alibaba.fastjson.JSON;
import com.lzx.elastic.search.model.Content;
import com.lzx.elastic.search.utils.HtmlParseUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class ContentServiceImpl implements IContentService {

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    //解析数据放入es索引库中
    @Override
    public Boolean parseContent(String keywords) {
        List<Content> goodsList = HtmlParseUtils.parseJD(keywords);
        if (Objects.nonNull(goodsList)) {
            //把查询到的数据放入es中
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.timeout("2m");
            for (Content content : goodsList) {
                bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(content), XContentType.JSON));
            }
            try {
                //请求es库
                BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                return !bulkResponse.hasFailures();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //获取数据，实现搜索功能
    @Override
    public List<Map<String, Object>> searchPage(String keywords, int pageNo, int pageSize) {
        if (pageNo <= 1) {
            pageNo = 1;
        }

        //条件搜索
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keywords);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        //关闭多个高亮
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        try {
            //执行搜索
            SearchRequest searchRequest = new SearchRequest("jd_goods");
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //解析结果
            List<Map<String, Object>> goodsList = new ArrayList<>();
            for (SearchHit documentFields : searchResponse.getHits().getHits()) {
                //解析高亮字段
                Map<String, HighlightField> highlightFieldMap = documentFields.getHighlightFields();
                HighlightField highlightField = highlightFieldMap.get("title");

                Map<String, Object> documentField = documentFields.getSourceAsMap();

                //解析高亮字段
                if (Objects.nonNull(highlightField)) {
                    Text[] texts = highlightField.fragments();
                    StringBuilder titleStringBuilder = new StringBuilder();

                    for (Text text : texts) {
                        titleStringBuilder.append(text);
                    }

                    documentField.put("title", titleStringBuilder);
                }

                goodsList.add(documentField);
            }

            //返回结果
            return goodsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
