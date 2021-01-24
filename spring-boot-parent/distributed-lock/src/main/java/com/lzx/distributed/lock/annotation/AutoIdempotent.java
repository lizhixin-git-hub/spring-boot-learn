package com.lzx.distributed.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 本篇博客介绍了使用springboot和拦截器、redis来优雅的实现接口幂等，对于幂等在实际的开发过程中是十分重要的，因为一个接口可能会被无数的客户端调用，如何保证其不影响后台的业务处理，如何保证其只影响数据一次是非常重要的，它可以防止产生脏数据或者乱数据，也可以减少并发量，实乃十分有益的一件事。而传统的做法是每次判断数据，这种做法不够智能化和自动化，比较麻烦。而今天的这种自动化处理也可以提升程序的伸缩性。
 * 自定义一个注解，定义此注解的主要目的是把它添加在需要实现幂等的方法上，凡是某个方法注解了它，都会实现自动幂等。后台利用反射如果扫描到这个注解，就会处理这个方法实现自动幂等，使用元注解ElementType.METHOD表示它只能放在方法上，etentionPolicy.RUNTIME表示它在运行时
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {

}
