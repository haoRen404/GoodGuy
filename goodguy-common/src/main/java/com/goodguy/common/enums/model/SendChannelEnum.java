package com.goodguy.common.enums.model;

import com.goodguy.common.utils.MyEnumUtil;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送渠道枚举，并嵌套了渠道下发送账号的枚举
 */
@Getter
// @ToString
public enum SendChannelEnum {

    SMS(10, "短信", Account.SMS.class),
    MAIL(20, "邮件", Account.MAIL.class),
    WECHAT_PUBLIC_ACCOUNT(30, "微信公众号", Account.WECHAT_PUBLIC_ACCOUNT.class),
    PUSH(40, "PUSH", Account.PUSH.class),
    ;

    /**
     * 获取渠道列表（不含发送账号信息）
     * @return
     */
    public static List<HashMap<String, Object>> findSendChanneList() {
        List<HashMap<String, Object>> res = new ArrayList<>();
        for (SendChannelEnum channel : SendChannelEnum.values()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", channel.getCode());
            map.put("name", channel.getName());
            res.add(map);
        }
        return res;
    }

    /**
     * 根据渠道code获取对应的发送账号列表
     * @param channelCode
     * @return
     */
    public static List<HashMap<String, Object>> findSendAccountList(Integer channelCode) {
        List<HashMap<String, Object>> res = new ArrayList<>();
        for (SendChannelEnum channel : SendChannelEnum.values()) {
            if (channel.getCode().equals(channelCode)) {
                for (Account account : channel.getAccounts()) {
                    HashMap<String, Object> m = new HashMap<>();
                    m.put("code", account.getCode());
                    m.put("name", account.getName());
                    res.add(m);
                }
            }
        }
        return res;
    }

    /**
     * 获取发送账号列表（含渠道code）
     * @return
     */
    public static List<HashMap<String, Object>> findSendAccountList() {
        List<HashMap<String, Object>> res = new ArrayList<>();
        for (SendChannelEnum channel : SendChannelEnum.values()) {
            Integer channelCode = channel.getCode();
            for (Account account : channel.getAccounts()) {
                HashMap<String, Object> m = new HashMap<>();
                m.put("channelCode", channelCode);
                m.put("code", account.getCode());
                m.put("name", account.getName());
                res.add(m);
            }
        }
        return res;
    }

    /**
     * 获取渠道和发送账号信息
     * @return
     */
    public static List<Map<String, Object>> enumToMapList() {
        List<Map<String, Object>> res = new ArrayList<>();
        for (SendChannelEnum channel : SendChannelEnum.values()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", channel.getCode());
            map.put("name", channel.getName());
            List<HashMap<String, Object>> list = new ArrayList<>();
            for (Account account : channel.getAccounts()) {
                HashMap<String, Object> m = new HashMap<>();
                m.put("code", account.getCode());
                m.put("name", account.getName());
                list.add(m);
            }
            map.put("account", list);
            res.add(map);
        }
        return res;
    }

    /**
     * 根据code值获取枚举常量
     * @param code code值
     * @return
     */
    public static SendChannelEnum findEnumConst(Integer code) {
        String name = MyEnumUtil.findConstantNameByCode(SendChannelEnum.class, code);
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return SendChannelEnum.valueOf(name);
    }



    /**
     * 参数：人群文件id类型状态码
     */
    private final Integer code;
    /**
     * 参数：状态码含义
     */
    private final String name;
    /**
     * 参数：账号
     */
    private final Account[] accounts;

    /**
     * 构造函数
     * @param code
     * @param name
     * @param account
     */
    SendChannelEnum(Integer code, String name, Class<? extends SendChannelEnum.Account> account) {
        this.code = code;
        this.name = name;
        this.accounts = account.getEnumConstants();
    }

    /**
     * getter
     */
    public Integer getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public Account[] getAccounts() {
        return accounts;
    }

    /**
     * 使用 interface 将子枚举类型组织起来
     */
    interface Account {
        Integer getCode();
        String getName();
        String toString();
        List<Account> getList();

        /**
         * 短信账号枚举
         */
        enum SMS implements Account {
            DEFAULT(10, "默认"),
            ;
            private Integer code;
            private String name;

            SMS(Integer code, String name) {
                this.code = code;
                this.name = name;
            }
            public String getName() {
                return name;
            }
            public Integer getCode() {
                return code;
            }
            @Override
            public String toString() {
                return "SMS{" +
                        "code=" + code +
                        ", name='" + name + '\'' +
                        '}';
            }

            @Override
            public List<Account> getList() {

                return null;
            }
        }

        /**
         * 邮件账号枚举
         */
        enum MAIL implements Account {
            DEFAULT(10,"默认"),
            ;
            private Integer code;
            private String name;

            MAIL(Integer code, String name) {
                this.code = code;
                this.name = name;
            }
            public Integer getCode() {
                return code;
            }
            public String getName() {
                return name;
            }
            @Override
            public String toString() {
                return "MAIL{" +
                        "code=" + code +
                        ", name='" + name + '\'' +
                        '}';
            }

            @Override
            public List<Account> getList() {
                return null;
            }
        }

        /**
         * 微信公众号账号枚举
         */
        enum WECHAT_PUBLIC_ACCOUNT implements Account {
            DEFAULT(10,"默认"),
            ;
            private Integer code;
            private String name;

            WECHAT_PUBLIC_ACCOUNT(Integer code, String name) {
                this.code = code;
                this.name = name;
            }
            public Integer getCode() {
                return code;
            }
            public String getName() {
                return name;
            }

            @Override
            public String toString() {
                return "WECHAT_PUBLIC_ACCOUNT{" +
                        "code=" + code +
                        ", name='" + name + '\'' +
                        '}';
            }

            @Override
            public List<Account> getList() {
                return null;
            }
        }

        /**
         * push账号枚举
         */
        enum PUSH implements Account {
            FIFTH(10,"默认"),
            ;
            private Integer code;
            private String name;

            PUSH(Integer code, String name) {
                this.code = code;
                this.name = name;
            }
            public Integer getCode() {
                return code;
            }
            public String getName() {
                return name;
            }
            @Override
            public String toString() {
                return "PUSH{" +
                        "code=" + code +
                        ", name='" + name + '\'' +
                        '}';
            }

            @Override
            public List<Account> getList() {
                return null;
            }
        }
    }
}
