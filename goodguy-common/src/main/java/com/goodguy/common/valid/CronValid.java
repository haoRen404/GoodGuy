package com.goodguy.common.valid;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义jsr303校验注解：校验是core表达式
 */
/**
 * message属性：这个属性被用来定义默认得消息模版, 当这个注解校验失败的时候,通过此属性来输出错误信息
 *      可以使用指定的错误描述文件中的信息来提示
 *
 * groups 属性：用于指定注解属于哪(些)个校验组, 这个的默认值必须是Class<?>类型到空到数组
 *
 * payload 属性：Bean Validation API 的使用者可以通过此属性来给约束条件指定严重级别. 这个属性并不被API自身所使用
 *
 * @Constraint(validatedBy = {ListValueConstraintValidator.class}) 关联自定义校验器
 * @Target：指定该注解可以被用在哪些位置(方法、字段、注解、构造器、方法参数等等)
 * @Retention(RUNTIME)：注解的保留位置，运行时通过反射被读取
 * @Documented：配置此注解出现在生成的javadoc中
 *
 */
@Documented
@Constraint(validatedBy = {CronConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface CronValid {
    /**
     * 校验不通过的时候打印的信息
     * @return
     */
    String message() default "属性值不为Core表达式";

    /**
     * 校验组，用于分组校验
     */
    Class<?>[] groups() default {};

    /**
     * 负载信息
     * @return
     */
    Class<? extends Payload> [] payload() default{};

    int[] vals() default {};
}
