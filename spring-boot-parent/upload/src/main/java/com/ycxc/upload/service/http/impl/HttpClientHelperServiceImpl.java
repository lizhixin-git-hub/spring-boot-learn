package com.ycxc.upload.service.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.ycxc.upload.service.http.IHttpClientHelperService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.touk.throwing.ThrowingBiConsumer;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("httpClientHelperService")
public class HttpClientHelperServiceImpl implements IHttpClientHelperService {

    private Logger LOGGER = LoggerFactory.getLogger(HttpClientHelperServiceImpl.class);

    private CloseableHttpClient httpClient;

    private RequestConfig requestConfig;

    @Autowired
    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Autowired
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    @Override
    public String get(String url, Map<String, Object> paramMap, Map<String, Object> header, String coding) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 创建一个request对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 配置连接参数
            httpGet.setConfig(requestConfig);
            //设置参数
            if (MapUtils.isNotEmpty(paramMap)) {
                List<NameValuePair> params = new ArrayList<>();
                paramMap.forEach(ThrowingBiConsumer.unchecked((k, v) -> params.add(new BasicNameValuePair(k, URLEncoder.encode(Optional
                        .ofNullable(v).map(String::valueOf).orElse(StringUtils.EMPTY), coding)))));
                String strParams = EntityUtils.toString(new UrlEncodedFormEntity(params));
                // 防止多参数时，分隔符","被转义
                String realParams = URLDecoder.decode(strParams, coding);
                httpGet.setURI(new URI(httpGet.getURI().toString().indexOf("?") > 0 ? httpGet.getURI().toString() + "&" + realParams
                        : httpGet.getURI().toString() + "?" + realParams));
            }
            // 设置头
            if (MapUtils.isNotEmpty(header)) {
                header.forEach((k, v) -> httpGet.addHeader(k, Optional.ofNullable(v)
                        .map(String::valueOf).orElse(StringUtils.EMPTY)));
            }
            // 执行request请求
            response = httpClient.execute(httpGet);
            return responseHandle(response);
        } catch (Exception e) {
            LOGGER.error("url : " + url + ", msg : " + e.getMessage());
            httpGet.abort();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //释放连接
            httpGet.releaseConnection();
        }
        return null;
    }

    @Override
    public String post(String url, Map<String, Object> paramMap, Map<String, Object> header, String coding) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 创建一个request对象
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            // 配置连接参数
            httpPost.setConfig(requestConfig);
            // 设置参数
            if (MapUtils.isNotEmpty(paramMap)) {
                List<NameValuePair> params = new ArrayList<>();
                paramMap.forEach((k, v) -> params.add(new BasicNameValuePair(k, Optional.ofNullable(v)
                        .map(String::valueOf).orElse(StringUtils.EMPTY))));
                HttpEntity entity = new UrlEncodedFormEntity(params, coding);
                httpPost.setEntity(entity);
            }
            // 设置头
            if (MapUtils.isNotEmpty(header)) {
                header.forEach((k, v) -> httpPost.addHeader(k, Optional.ofNullable(v)
                        .map(String::valueOf).orElse(StringUtils.EMPTY)));
            }
            // 执行request请求
            response = httpClient.execute(httpPost);
            return responseHandle(response);
        } catch (Exception e) {
            LOGGER.error("url : " + url + ", msg : " + e.getMessage());
            httpPost.abort();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //释放连接
            httpPost.releaseConnection();
        }
        return null;
    }

    @Override
    public String postJSON(String url, String json_str, Map<String, Object> header, String coding) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 创建一个request对象
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            // 配置连接参数
            httpPost.setConfig(requestConfig);
            // 设置参数
            if (StringUtils.isNotBlank(json_str)) {
                StringEntity entity = new StringEntity(json_str, ContentType.APPLICATION_JSON);
                entity.setContentEncoding(coding);
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            // 设置头
            if (MapUtils.isNotEmpty(header)) {
                header.forEach((k, v) -> httpPost.addHeader(k, Optional.ofNullable(v)
                        .map(String::valueOf).orElse(StringUtils.EMPTY)));
            }
            // 执行request请求
            response = httpClient.execute(httpPost);
            return responseHandle(response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("url : " + url + ", msg : " + e.getMessage() + ", param : " + json_str);
            httpPost.abort();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //释放连接
            httpPost.releaseConnection();
        }
        return null;
    }

    @Override
    public String uploadFile(String url, String filePath, String fileParam, Map<String, Object> params) {
        File file = new File(filePath);
        if (!(file.exists() && file.isFile())) {
            throw new RuntimeException("file : file is null");
        }
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 创建一个request对象
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            // 配置连接参数
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody(fileParam, file, ContentType.DEFAULT_BINARY, file.getName());
            if (MapUtils.isNotEmpty(params)) {
                params.forEach((k, v) -> builder.addTextBody(k, Optional.ofNullable(v)
                        .map(String::valueOf).orElse(StringUtils.EMPTY), ContentType.create("text/plain", Consts.UTF_8)));
            }
            HttpEntity requestEntity = builder.build();
            httpPost.setEntity(requestEntity);
            // 执行request请求
            response = httpClient.execute(httpPost);
            return responseHandle(response);
        } catch (Exception e) {
            httpPost.abort();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //释放连接
            httpPost.releaseConnection();
        }
        return null;
    }

    /**
     * 请求响应解析
     *
     * @param response CloseableHttpResponse对象
     * @return 响应内容
     */
    private String responseHandle(CloseableHttpResponse response) {
        // 获取响应体
        HttpEntity httpEntity = null;
        try {
            // 获取响应状态
            int statusCode = Optional.ofNullable(response).map(CloseableHttpResponse::getStatusLine)
                    .map(StatusLine::getStatusCode).orElse(NumberUtils.INTEGER_ZERO);
            // 获取响应体
            httpEntity = Optional.ofNullable(response).map(CloseableHttpResponse::getEntity).orElse(null);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("httpStatus",statusCode);
            if (httpEntity != null) {
                jsonObject.put("execute",EntityUtils.toString(httpEntity));
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientHelper responseHandle error ", e);
        } finally {
            // 如果httpEntity没有被完全消耗，那么连接无法安全重复使用，将被关闭并丢弃
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
