package com.goodguy.common.enums.model;

import com.goodguy.common.utils.MyEnumUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 消息类型枚举
 * 10-通知类消息 20-营销类消息 30-验证码类消息
 */
public enum MsgTypeEnum {
    NOTIFY(10, "通知类"),
    MARKETING(20, "营销类"),
    VERIFICATION_CODE(30, "验证码类")
    ;

    /**
     * 获取枚举常量列表
     * @return
     */
    public static List<Map<String, Object>> enumToMapList() {
        return MyEnumUtil.enumToMapList(MsgTypeEnum.class);
    }

    /**
     * 根据code值获取枚举常量
     * @param code
     * @return
     */
    public static MsgTypeEnum findEnumConst(Integer code) {
        String name = MyEnumUtil.findConstantNameByCode(MsgTypeEnum.class, code);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return MsgTypeEnum.valueOf(name);
    }

    /**
     * 审核状态码
     */
    private final Integer code;
    /**
     * 状态码含义
     */
    private final String name;

    private MsgTypeEnum(Integer code, String name){
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
        return "MsgTypeEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
