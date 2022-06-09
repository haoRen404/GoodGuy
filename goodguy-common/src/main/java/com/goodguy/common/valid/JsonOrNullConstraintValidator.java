package com.goodguy.common.valid;

import com.goodguy.common.utils.CommonUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsonOrNullConstraintValidator implements ConstraintValidator<JsonOrNullValid, String>  {
    private JsonOrNullValid coreValid;

    /**
     * 初始化注解中数据存储起来
     * @param constraintAnnotation
     */
    @Override
    public void initialize(JsonOrNullValid constraintAnnotation) {
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
            return true;

        // 删除两端空格
        s = s.trim();

        if ("".equals(s))
            return true;

        return CommonUtil.isJson(s);
    }
}
