package com.goodguy.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.goodguy.common.enums.ResponseStatusEnum;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 构造方法
     */
    public R() {
        put("code", ResponseStatusEnum.SUCCESS.getCode());
        put("msg", ResponseStatusEnum.SUCCESS.getMsg());
    }

    /**
     * 利用fastjson进行反序列化
     * @return
     */
    public HashMap getData() {
        // 默认是map
        Object data = get("data");
        String jsonString = JSON.toJSONString(data);
        HashMap map = new HashMap();
        return JSON.parseObject(jsonString, HashMap.class);
    }
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
     */
    public R setData(Object data) {
        put("data", data);
        return this;
    }
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 获取数据
     */
    public Integer getCode(){
        return (Integer) this.get("code");
    }

    /**
     * 请求成功
     */
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }
    public static R ok() {
        return new R();
    }

    /**
     * 请求失败
     */
    public static R fail() {
        return fail(ResponseStatusEnum.FAIL.getCode(), ResponseStatusEnum.FAIL.getMsg());
    }
    public static R fail(String msg) {
        return fail(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
    public static R fail(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    public static R fail(ResponseStatusEnum responseStatusEnum) {
        R r = new R();
        r.put("code", responseStatusEnum.getCode());
        r.put("msg", responseStatusEnum.getMsg());
        return r;
    }

}
