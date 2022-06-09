package com.goodguy.common.valid;

import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解校验器：校验是core表达式
 * 需要指定泛型：ConstraintValidator<A extends Annotation, T> A:校验注解，T代表被校验元素的类型
 */
public class CronConstraintValidator implements ConstraintValidator<CronValid, String> {

    private CronValid coreValid;

    /**
     * 初始化注解中数据存储起来
     * @param constraintAnnotation
     */
    @Override
    public void initialize(CronValid constraintAnnotation) {
        coreValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return association(s);
    }

    /**
     * 校验逻辑
     * @param s
     * @return
     */
    private boolean association(String s) {
        if (s == null)
            return false;

        // 删除两端空格
        s = s.trim();
        // 1有特殊含义
        if ("1".equals(s))
            return true;

        // 使用 quartz 进行 cron 表达式验证
        return CronExpression.isValidExpression(s);
    }
}
