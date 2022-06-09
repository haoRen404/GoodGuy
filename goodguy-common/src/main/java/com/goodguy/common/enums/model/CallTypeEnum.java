package com.goodguy.common.enums.model;

import com.goodguy.common.utils.MyEnumUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 调用类型枚举
 */
public enum CallTypeEnum {

    OPERATION(10, "运营类"),
    TECHNOLOGY(20, "技术调用类"),
    ;

    /**
     * 获取枚举常量列表
     * @return
     */
    public static List<Map<String, Object>> enumToMapList() {
        return MyEnumUtil.enumToMapList(CallTypeEnum.class);
    }

    /**
     * 根据code值获取枚举常量
     * @param code
     * @return
     */
    public static CallTypeEnum findEnumConst(Integer code) {
        String name = MyEnumUtil.findConstantNameByCode(CallTypeEnum.class, code);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return CallTypeEnum.valueOf(name);
    }

    /**
     * 人群文件id类型状态码
     */
    private final Integer code;
    /**
     * 状态码含义
     */
    private final String name;

    private CallTypeEnum(Integer code, String name){
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
        return "CallTypeEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
