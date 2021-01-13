package com.lzx.validation.annotation;

import com.lzx.validation.annotation.impl.ListValueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 虽然JSR303和springboot-validator 已经提供了很多校验注解，但是当面对复杂参数校验时，还是不能满足我们的要求，这时候我们就需要 自定义校验注解。
 * <p>
 * 例如User中的gender，用 1代表男 2代表女，自定义一个校验注解@ListValue，指定取值只能1和2
 *
 * message属性, 这个属性被用来定义默认得消息模版, 当这个约束条件被验证失败的时候,通过 此属性来输出错误信息。
 * groups 属性, 用于指定这个约束条件属于哪(些)个校验组. 这个的默认值必须是Class<?>类型数组。
 * payload 属性, Bean Validation API 的使用者可以通过此属性来给约束条件指定严重级别. 这个属性并不被API自身所使用。
 * 除了这三个强制性要求的属性(message, groups 和 payload) 之外, 我们还添 加了一个属性用来指定所要求的值. 此属性的名称vals在annotation的定义中比较特 殊, 如果只有这个属性被赋值了的话, 那么, 在使用此annotation到时候可以忽略此属性名称.
 *
 * 另外, 我们还给这个annotation标注了一些元标注( meta annotations)：
 *
 * //@Target({ METHOD, FIELD, ANNOTATION_TYPE }): 表示此注解可以被用在方法, 字段或者 annotation声明上。
 * //@Retention(RUNTIME): 表示这个标注信息是在运行期通过反射被读取的.
 * //@Constraint(validatedBy = ListValueConstraintValidator.class): 指明使用哪个校验器(类) 去校验使用了此标注的元素.
 * //@Documented: 表示在对使用了该注解的类进行javadoc操作到时候, 这个标注会被添加到 javadoc当中.
 * 创建约束校验器
 */
@Documented
@Constraint(validatedBy = {ListValueConstraintValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] vals() default {};

}
