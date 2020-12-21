package com.lzx.shiro.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS配置方式
 * https://mp.weixin.qq.com/s/VYbaSamewbiNn-Vj6waHDg
 * 2.1注解方式：
 * 在需要跨域的Controller层的类或方法上加上注解：@CrossOrigin。这样就可以指定该controller中所有方法或某个方法处理所有或只来自http://127.0.0.1:8086中的请求。
 * //@CrossOrigin(origins = "http://127.0.0.1:8086", maxAge = 3600)
 * 2.2 过滤器方式
 * //@Component
 * public class CorsFilter implements Filter {
 *
 *     private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);
 *
 *     //@Override
 *     public void init(FilterConfig filterConfig) throws ServletException {
 *
 *     }
 *
 *     //@Override
 *     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
 *             throws IOException, ServletException {
 *
 *         HttpServletResponse response = (HttpServletResponse) res;
 *         response.setHeader("Access-Control-Allow-Origin", "*");
 *         response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
 *         response.setHeader("Access-Control-Max-Age", "3600");
 *         response.setHeader("Access-Control-Allow-Headers",
 *                 "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
 *
 *         chain.doFilter(req, res);
 *     }
 *
 *     //@Override
 *     public void destroy() {
 *
 *     }
 *
 * }
 * 2.3 继承WebMvcConfigurerAdapter，重写addCorsMappings方法
 */
@Component
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
