package com.goodguy.common.enums.model;

import com.goodguy.common.utils.MyEnumUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 人群文件id类型
 */
public enum CrowdIdTypeEnum {

    PHONE_NUMBER(10, "手机号码"),
    EMAIL_ADDRESS(20, "邮箱地址"),
    WECHAT_OPENID(30, "微信openid"),
    PUSH_TOKEN(40, "push token"),
    ;

    /**
     * 获取枚举常量列表
     * @return
     */
    public static List<Map<String, Object>> enumToMapList() {
        return MyEnumUtil.enumToMapList(CrowdIdTypeEnum.class);
    }

    /**
     * 根据code值获取枚举常量
     * @param code
     * @return
     */
    public static CrowdIdTypeEnum findEnumConst(Integer code) {
        String name = MyEnumUtil.findConstantNameByCode(CrowdIdTypeEnum.class, code);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return CrowdIdTypeEnum.valueOf(name);
    }

    /**
     * 人群文件id类型状态码
     */
    private final Integer code;
    /**
     * 状态码含义
     */
    private final String name;

    private CrowdIdTypeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }
    public Integer getCode() {
        return this.code;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "CrowdIdTypeEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
