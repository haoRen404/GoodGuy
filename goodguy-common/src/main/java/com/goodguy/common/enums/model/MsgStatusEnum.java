package com.goodguy.common.enums.model;

import com.goodguy.common.utils.MyEnumUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 消息状态枚举
 * 当前消息状态：0-新建；10-停用；20-启用；30-等待发送；40-发送中；50-发送成功；60-发送失败
 */
public enum MsgStatusEnum {
    TECHNOLOGY(10, "技术调用"),
    NEW(20, "新建"),
    WAIT(30, "等待发送"),
    STOP(40, "停用"),
    SENDING(50, "发送中"),
    SUCCESS(60, "发送成功"),
    FAIL(70, "发送失败")
    ;

    /**
     * 获取枚举常量列表
     * @return
     */
    public static List<Map<String, Object>> enumToMapList() {
        return MyEnumUtil.enumToMapList(MsgStatusEnum.class);
    }

    /**
     * 根据code值获取枚举常量
     * @param code
     * @return
     */
    public static MsgStatusEnum findEnumConst(Integer code) {
        String name = MyEnumUtil.findConstantNameByCode(MsgStatusEnum.class, code);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return MsgStatusEnum.valueOf(name);
    }

    /**
     * 审核状态码
     */
    private Integer code;
    /**
     * 状态码含义
     */
    private String name;

    private MsgStatusEnum(){

    }
    private MsgStatusEnum(Integer code, String name){
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
        return "MsgStatusEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
