package com.goodguy.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.goodguy.common.enums.ResponseStatusEnum;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class R2 extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 构造方法
     */
    public R2() {
        put("code", ResponseStatusEnum.SUCCESS.getCode());
        put("msg", ResponseStatusEnum.SUCCESS.getMsg());
    }

    /**
     * 利用fastjson进行反序列化
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T getData(TypeReference<T> typeReference) {
        // 默认是map
        Object data = get("data");
        String jsonString = JSON.toJSONString(data);
        return JSON.parseObject(jsonString, typeReference);
    }
    public <T> T getData(String key, TypeReference<T> typeReference) {
        // 默认是map
        Object data = get(key);
        String jsonString = JSON.toJSONString(data);
        return JSON.parseObject(jsonString, typeReference);
    }

    /**
     * 添加数据
     * @param data
     * @return
     */
    public R2 setData(Object data) {
        put("data", data);
        return this;
    }
    public R2 put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 获取数据
     * @return
     */
    public Integer getCode(){
        return (Integer) this.get("code");
    }

    /**
     * 请求成功
     * @param msg
     * @return
     */
    public static R2 ok(String msg) {
        R2 r = new R2();
        r.put("msg", msg);
        return r;
    }
    public static R2 ok(Map<String, Object> map) {
        R2 r = new R2();
        r.putAll(map);
        return r;
    }
    public static R2 ok() {
        return new R2();
    }
    public static R2 ok(ResponseStatusEnum responseStatusEnum) {
        R2 r = new R2();
        r.put("code", responseStatusEnum.getCode());
        r.put("msg", responseStatusEnum.getMsg());
        return r;
    }

    /**
     * 请求失败
     * @return
     */
    public static R2 fail() {
        return fail(ResponseStatusEnum.FAIL.getCode(), ResponseStatusEnum.FAIL.getMsg());
    }
    public static R2 fail(String msg) {
        return fail(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
    public static R2 fail(int code, String msg) {
        R2 r = new R2();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    public static R2 fail(ResponseStatusEnum responseStatusEnum) {
        R2 r = new R2();
        r.put("code", responseStatusEnum.getCode());
        r.put("msg", responseStatusEnum.getMsg());
        return r;
    }
}
