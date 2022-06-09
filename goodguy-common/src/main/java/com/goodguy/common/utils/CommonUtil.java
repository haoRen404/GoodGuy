package com.goodguy.common.utils;

import com.alibaba.fastjson.JSONObject;


public class CommonUtil {
    /**
     * 获取 NanoId
     */
    public static String getNanoId() {
        return NanoIdUtils.randomNanoId();
    }

    /**
     * 判断某字符串是否是json字符串
     * @param str
     * @return
     */
    public static boolean isJson(String str){
        try {
            JSONObject jsonStr= JSONObject.parseObject(str);
            return  true;
        } catch (Exception e) {
            return false;
        }

    }

}
