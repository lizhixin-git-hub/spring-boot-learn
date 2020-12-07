package com.ycxc.upload.service.http;

import java.util.Map;

public interface IHttpClientHelperService {

    /**
     * get请求
     *
     * @param url      请求路径
     * @param paramMap 参数map
     * @param header   请求头信息map
     * @param coding   字符编码
     * @return 响应内容
     */
    String get(String url, Map<String, Object> paramMap, Map<String, Object> header, String coding);

    /**
     * post请求
     *
     * @param url      请求路径
     * @param paramMap 参数map
     * @param header   请求头信息map
     * @param coding 字符编码
     * @return 响应内容
     */
    String post(String url, Map<String, Object> paramMap, Map<String, Object> header, String coding);

    /**
     * json数据请求
     *
     * @param url     请求路径
     * @param jsonStr json格式参数
     * @param header  请求头信息map
     * @param coding  字符编码
     * @return 响应内容
     */
    String postJSON(String url, String jsonStr, Map<String, Object> header, String coding);

    /**
     * 本地文件上传请求
     *
     * @param url       请求路径
     * @param filePath  本地文件路径
     * @param fileParam 文件part名称
     * @param params    请求参数map
     * @return 响应内容
     */
    String uploadFile(String url, String filePath, String fileParam, Map<String, Object> params);

}
