package com.goodguy.api.vo;

import com.goodguy.common.enums.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 流程处理的结果
 * @param <T>
 */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BasicResultVO<T> {
    /**
     * 响应状态
     */
    private Integer status;

    /**
     * 响应编码
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 构造函数
     */

    public BasicResultVO(ResponseStatusEnum status) {
        this(status, null);
    }
    public BasicResultVO(ResponseStatusEnum status, T data) {
        this(status, status.getMsg(), data);
    }
    public BasicResultVO(ResponseStatusEnum status, String msg, T data) {
        this.status = status.getCode();
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功
     * @return
     */
    public static BasicResultVO<Void> success() {
        return new BasicResultVO<>(ResponseStatusEnum.SUCCESS);
    }
    public static <T> BasicResultVO<T> success(String msg) {
        return new BasicResultVO<T>(ResponseStatusEnum.SUCCESS, msg, null);
    }
    public static <T> BasicResultVO<T> success(T data) {
        return new BasicResultVO<>(ResponseStatusEnum.SUCCESS, data);
    }
    public static <T> BasicResultVO<T> success(String msg, T data) {
        return new BasicResultVO<>(ResponseStatusEnum.SUCCESS, msg, data);
    }

    /**
     * 响应失败
     */
    public static BasicResultVO<Void> fail() {
        return new BasicResultVO<>(ResponseStatusEnum.FAIL);
    }
    public static <T> BasicResultVO<T> fail(String msg) {
        return new BasicResultVO<T>(ResponseStatusEnum.FAIL, msg, null);
    }
    public static <T> BasicResultVO<T> fail(T data) {
        return new BasicResultVO<>(ResponseStatusEnum.FAIL, data);
    }
    public static <T> BasicResultVO<T> fail(String msg, T data) {
        return new BasicResultVO<>(ResponseStatusEnum.FAIL, msg, data);
    }
}
