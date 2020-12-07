package com.ycxc.upload.config;

import com.ycxc.upload.evictor.IdleConnectionEvictor;
import com.ycxc.upload.config.properties.HttpClientProperties;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    private final HttpClientProperties httpClientProperties;

    public HttpClientConfig(@Autowired HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }

    /**
     * 创建连接池管理器
     * @return 连接池管理器
     */
    @Bean
    public PoolingHttpClientConnectionManager createPoolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolManager = new PoolingHttpClientConnectionManager();
        poolManager.setMaxTotal(httpClientProperties.getMaxTotal());
        poolManager.setDefaultMaxPerRoute(httpClientProperties.getDefaultMaxPerRoute());
        poolManager.setValidateAfterInactivity(httpClientProperties.getValidateAfterInactivity());
        return poolManager;
    }

    /**
     * 实例化连接池，设置连接池管理器。
     * 这里需要以参数形式注入上面实例化的连接池管理器
     * @param httpClientConnectionManager 连接池管理
     * @return httpClient构造器
     */
    @Bean
    public HttpClientBuilder getHttpClientBuilder(@Autowired PoolingHttpClientConnectionManager httpClientConnectionManager) {
        //HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager);
        httpClientBuilder.setKeepAliveStrategy((response, context) -> {
            HeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (iterator.hasNext()) {
                HeaderElement headerElement = iterator.nextElement();
                String param = headerElement.getName();
                String value = headerElement.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 30 * 1000;
        });
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(httpClientProperties.getRetryCount(), false));
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     * @param httpClientBuilder httpClient构造器
     * @return httpClient对象
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Autowired HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     * @return  RequestConfig配置构造器
     */
    @Bean
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(httpClientProperties.getConnectTimeout())// 从连接池中取连接的超时时间
                .setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout())// 连接超时时间
                .setSocketTimeout(httpClientProperties.getSocketTimeout())// 请求超时时间
                .setStaleConnectionCheckEnabled(httpClientProperties.isStaleConnectionCheckEnabled());
    }

    /**
     * 使用builder构建一个RequestConfig对象
     * @param builder RequestConfig配置构造器
     * @return 请求配置
     */
    @Bean
    public RequestConfig createRequestConfig(@Autowired RequestConfig.Builder builder) {
        return builder.build();
    }

    /**
     * 定期清理无效连接
     * @param poolManager 连接池管理
     * @return 定期清理无效的http连接对象
     */
    @Bean(destroyMethod = "shutdown")
    public IdleConnectionEvictor createIdleConnectionEvictor(@Autowired PoolingHttpClientConnectionManager poolManager) {
        return new IdleConnectionEvictor(poolManager, httpClientProperties.getWaitTime(), httpClientProperties.getIdleConTime());
    }

}
