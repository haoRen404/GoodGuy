package com.goodguy.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {JsonOrNullConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface JsonOrNullValid {
    /**
     * 校验不通过的时候打印的信息
     * @return
     */
    String message() default "属性值应为json格式或null";

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
