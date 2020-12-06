package com.lzx.elastic.search;

import com.alibaba.fastjson.JSON;
import com.lzx.elastic.search.model.Content;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchTests {

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    //创建索引
    @Test
    public void testCreateIndex() throws IOException {
        //1、创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("jd_goods");
        //2、客户端执行请求 IndicesClient
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        //3、输出返回
        System.out.println(createIndexResponse);
    }

    //测试获取索引
    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("jd_goods");
        boolean exist = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exist);
    }

    //测试删除索引
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("jd_goods");
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());
    }

    //测试添加文档
    @Test
    public void testAddDocument() throws IOException {
        //创建对象
        Content content = new Content();
        content.setTitle("dkny秋季纯色a字蕾丝dd商场同款连衣");
        content.setPrice("50");
        content.setImg("https://img.alicdn.com/bao/uploaded/i1/3899981502/O1CN01q1uVx21MxxSZs8TVn_!!0-item_pic.jpg");
        //创建请求
        IndexRequest indexRequest = new IndexRequest("jd_goods");
        //设置规则
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));

        //将数据放入请求
        indexRequest.source(JSON.toJSONString(content), XContentType.JSON);

        //发送请求
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        //打印结果
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }

    //测试获取文档，判断是否存在
    @Test
    public void testIsExists() throws IOException {
        GetRequest getRequest = new GetRequest("jd_goods", "1");
        //不获取返回的_source 的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //测试获取文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("jd_goods", "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse);
    }

    //测试更新文档信息
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("jd_goods", "1");
        updateRequest.timeout(TimeValue.timeValueSeconds(1));

        Content content = new Content();
        content.setPrice("100");

        updateRequest.doc(JSON.toJSONString(content), XContentType.JSON);

        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
        System.out.println(updateResponse);
    }

    //测试删除文档信息
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("jd_goods", "1");
        deleteRequest.timeout(TimeValue.timeValueSeconds(1));

        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(deleteResponse.status());
        System.out.println(deleteResponse);
    }

    //测试批量添加文档信息
    @Test
    public void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        List<Content> goodsList = new ArrayList<>();
        Content content = new Content();
        content.setTitle("dkny秋季纯色a字蕾丝dd商场同款连衣");
        content.setPrice("50");
        content.setImg("https://img.alicdn.com/bao/uploaded/i1/3899981502/O1CN01q1uVx21MxxSZs8TVn_!!0-item_pic.jpg");
        goodsList.add(content);

        for (int i = 0; i<goodsList.size(); i++) {
            //批量更新和批量删除，就在这里修改对应的请求就可以了
            bulkRequest.add(new IndexRequest("jd_goods")
                    //不写生成随机ID
                    .id(Objects.toString(i+1))
                    .source(JSON.toJSONString(goodsList.get(i)), XContentType.JSON));
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        //返回false,代表成功
        System.out.println(bulkResponse.hasFailures());
        System.out.println(bulkResponse);
    }

    //测试搜索
    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("jd_goods");

        //构建所搜条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //精确查询,匹配所有：MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        QueryBuilder queryBuilder = QueryBuilders.termQuery("name", "zhangsan");

        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(searchResponse.getHits()));

        for (SearchHit searchHit : searchResponse.getHits()){
            System.out.println(searchHit.getSourceAsMap());
        }
    }
}
