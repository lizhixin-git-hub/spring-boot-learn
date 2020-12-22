package security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * 在项目启动过程中，我们会看到如下一行日志:
     * 这就是 Spring Security 为默认用户 user 生成的临时密码，是一个 UUID 字符串。
     * 接下来我们去访问 http://localhost:8080/hello 接口，就可以看到自动重定向到登录页面了：
     * 和用户相关的自动化配置类在 UserDetailsServiceAutoConfiguration 里边，在该类的 getOrDeducePassword 方法中:
     * Using generated security password: 362d81ae-de3e-42fd-92a9-76471915a5ab日志打印
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "hello2";
    }

    @RequestMapping("/f1")
    public String f1() {
        return "f1";
    }

    @RequestMapping("/f2")
    public String f2() {
        return "f2";
    }

}
