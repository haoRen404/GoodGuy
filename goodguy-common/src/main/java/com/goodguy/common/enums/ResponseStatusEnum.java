package com.goodguy.common.enums;

/**
 * 响应状态枚举
 */
// @Getter
// @ToString
// @NoArgsConstructor
// @AllArgsConstructor
public enum ResponseStatusEnum {
    /**
     * OK：操作成功
     */
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),


    PARAMETER_CHECK_FAIL(1000, "参数校验失败"),
    LACK_PARAMETER(1001, "缺少必要参数"),
    DATABASE_OPERATION_FAIL(1002, "数据库操作失败"),
    DATA_NOT_EXIST(1003, "数据不存在"),
    EDIT_FAILED(1004, "编辑失败"),


    ;


    /**
     * 响应状态
     */
    private Integer code;
    /**
     * 响应编码
     */
    private String msg;


    private ResponseStatusEnum(){

    }
    private ResponseStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "RespStatusEnum{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
