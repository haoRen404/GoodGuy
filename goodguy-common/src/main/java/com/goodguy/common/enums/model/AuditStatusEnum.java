package com.goodguy.common.enums.model;

import com.goodguy.common.utils.MyEnumUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 审核状态枚举类
 */
public enum AuditStatusEnum {
    NO_NEED_AUDIT(10, "无需审核"),
    WAIT_AUDIT(20, "待审核"),
    UNDER_AUDIT(30, "审核中"),
    SUCCESS(40, "审核成功"),
    REJECTED(50, "被拒绝"),
    ;

    /**
     * 获取枚举常量列表
     * @return
     */
    public static List<Map<String, Object>> enumToMapList() {
        return MyEnumUtil.enumToMapList(AuditStatusEnum.class);
    }

    /**
     * 根据code值获取枚举常量
     * @param code
     * @return
     */
    public static AuditStatusEnum findEnumConst(Integer code) {
        String name = MyEnumUtil.findConstantNameByCode(AuditStatusEnum.class, code);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return AuditStatusEnum.valueOf(name);
    }

    /**
     * 人群文件id类型状态码
     */
    private final Integer code;
    /**
     * 状态码含义
     */
    private final String name;

    private AuditStatusEnum(Integer code, String name){
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
        return "AuditStatusEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
