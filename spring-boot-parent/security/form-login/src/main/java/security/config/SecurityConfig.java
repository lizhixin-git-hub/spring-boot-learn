package security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 为了增加密码的安全性，一般在密码加密过程中还需要加盐，所谓的盐可以是一个随机数也可以是用户名，加盐之后，即使密码明文相同的用户生成的密码密文也不相同，这可以极大的提高密码的安全性。但是传统的加盐方式需要在数据库中有专门的字段来记录盐值，这个字段可能是用户名字段（因为用户名唯一），也可能是一个专门记录盐值的字段，这样的配置比较繁琐
     * 1、Spring Security 提供了多种密码加密方案，官方推荐使用 BCryptPasswordEncoder，BCryptPasswordEncoder 使用 BCrypt 强哈希函数，开发者在使用时可以选择提供 strength 和 SecureRandom 实例。strength 越大，密钥的迭代次数越多，密钥迭代次数为 2^strength。strength 取值在 4~31 之间，默认为 10。
     * 2、不同于 Shiro 中需要自己处理密码加盐，在 Spring Security 中，BCryptPasswordEncoder 就自带了盐，处理起来非常方便。
     * 3、而 BCryptPasswordEncoder 就是 PasswordEncoder 接口的实现类
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        //Spring Security 5.0之前默认的PasswordEncoder实现类NoOpPasswordEncoder,这个类已经被标记为过时了,因为不安全,替换的类为：DelegatingPasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * 下面相当于配置文件中配置：spring.security.user.name=javaboy/spring.security.user.password=123用户名与密码，覆盖项目启动的初始值user/随机密码：
         * 1、首先我们自定义 SecurityConfig 继承自 WebSecurityConfigurerAdapter，重写里边的 configure 方法。
         * 2、首先我们提供了一个 PasswordEncoder 的实例，因为目前的案例还比较简单，因此我暂时先不给密码进行加密，所以返回 NoOpPasswordEncoder 的实例即可。
         * 3、configure 方法中，我们通过 inMemoryAuthentication 来开启在内存中定义用户，withUser 中是用户名，password 中则是用户密码，roles 中是用户角色。
         * 4、如果需要配置多个用户，用 and 相连：
         * 在没有 Spring Boot 的时候，我们都是 SSM 中使用 Spring Security，这种时候都是在 XML 文件中配置 Spring Security，既然是 XML 文件，标签就有开始有结束，现在的 and 符号相当于就是 XML 标签的结束符，表示结束当前标签，这是个时候上下文会回到 inMemoryAuthentication 方法中，然后开启新用户的配置
         */
        auth.inMemoryAuthentication()
                .withUser("javaboy")
                .password("123").roles("admin");
    }

    /**
     * 1、web.ignoring() 用来配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作。
     * 2、如果我们使用 XML 来配置 Spring Security ，里边会有一个重要的标签 <http>，HttpSecurity 提供的配置方法 都对应了该标签。
     * 3、authorizeRequests 对应了 <intercept-url>。
     * 4、formLogin 对应了 <formlogin>。
     * 5、and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置。
     * 6、permitAll 表示登录相关的页面/接口不要被拦截。
     * 7、最后记得关闭 csrf ，关于 csrf 问题我到后面专门说。
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    /**
     * 1、在 Spring Security 中，如果我们不做任何配置，默认的登录页面和登录接口的地址都是 /login，也就是说，默认会存在如下两个请求：
     * GET http://localhost:8080/login
     * POST http://localhost:8080/login
     * 如果是 GET 请求表示你想访问登录页面，如果是 POST 请求，表示你想提交登录数据
     * 当我们配置了 loginPage 为 /login.html 之后，这个配置从字面上理解，就是设置登录页面的地址为 /login.html。
     * 2、实际上它还有一个隐藏的操作，就是登录接口地址也设置成 /login.html 了。换句话说，新的登录页面和登录接口地址都是 /login.html，现在存在如下两个请求：
     * GET http://localhost:8080/login.html
     * POST http://localhost:8080/login.html
     * 前面的 GET 请求用来获取登录页面，后面的 POST 请求用来提交登录数据。
     * 3、为什么登录页面和登录接口不能分开配置呢？
     * 其实是可以分开配置的！
     * 在 SecurityConfig 中，我们可以通过 loginProcessingUrl 方法来指定登录接口地址，如下：
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //这样配置之后，登录页面地址和登录接口地址就分开了，各是各的
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                //修改用户名、密码的表单参数
                .usernameParameter("name")
                .passwordParameter("passwd")
                //前后端不分离的登陆成功回调
                //注意：实际操作中，defaultSuccessUrl 和 successForwardUrl 只需要配置一个即可,defaultSuccessUrl 和 successForwardUrl 只需要配置一个即可，具体配置哪个，则要看你的需求，两个的区别如下
                //首先我们在配置的时候，defaultSuccessUrl 和 successForwardUrl 只需要配置一个即可，具体配置哪个，则要看你的需求，两个的区别如下：
                //1、defaultSuccessUrl 有一个重载的方法，我们先说一个参数的 defaultSuccessUrl 方法。如果我们在 defaultSuccessUrl 中指定登录成功的跳转页面为 /index，此时分两种情况，如果你是直接在浏览器中输入的登录地址，登录成功后，就直接跳转到 /index，如果你是在浏览器中输入了其他地址，例如 http://localhost:8080/hello，结果因为没有登录，又重定向到登录页面，此时登录成功后，就不会来到 /index ，而是来到 /hello 页面。
                //2、defaultSuccessUrl 还有一个重载的方法，第二个参数如果不设置默认为 false，也就是我们上面的的情况，如果手动设置第二个参数为 true，则 defaultSuccessUrl 的效果和 successForwardUrl 一致。
                //3、successForwardUrl 表示不管你是从哪里来的，登录后一律跳转到 successForwardUrl 指定的地址。例如 successForwardUrl 指定的地址为 /index ，你在浏览器地址栏输入 http://localhost:8080/hello，结果因为没有登录，重定向到登录页面，当你登录成功之后，就会服务端跳转到 /index 页面；或者你直接就在浏览器输入了登录页面地址，登录成功后也是来到 /index。
                .defaultSuccessUrl("/index")
                .successForwardUrl("/index")
                //与登录成功相似，登录失败也是有两个方法：failureForwardUrl、failureUrl
                //「这两个方法在设置的时候也是设置一个即可」。failureForwardUrl 是登录失败之后会发生服务端跳转，failureUrl 则在登录失败之后，会发生重定向
                .failureForwardUrl("/f2")
                .failureUrl("/f1")
                .permitAll()
                .and()
                //注销登录的默认接口是 /logout，我们也可以配置。
                /*
                 * 1、默认注销的 URL 是 /logout，是一个 GET 请求，我们可以通过 logoutUrl 方法来修改默认的注销 URL。
                 * 2、logoutRequestMatcher 方法不仅可以修改注销 URL，还可以修改请求方式，实际项目中，这个方法和 logoutUrl 任意设置一个即可。
                 * 3、logoutSuccessUrl 表示注销成功后要跳转的页面。
                 * 4、deleteCookies 用来清除 cookie。
                 * 5、clearAuthentication 和 invalidateHttpSession 分别表示清除认证信息和使 HttpSession 失效，默认可以不用配置，默认就会清除。
                 */
                .logout()
                //.logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .logoutSuccessUrl("/index")
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }

}
