package com.goodguy.common.utils;

import com.goodguy.common.enums.model.MsgStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 参考：https://blog.csdn.net/llwutong/article/details/116456815
 */

/**
 * 枚举工具类
 */
public class MyEnumUtil {

    /**
     * 枚举类全路径名前缀
     */
    private static final String ENUM_CLASSPATH = "java.lang.Enum";

    /**
     * 判断是否是
     * @param enumClass
     * @return
     */
    private static boolean isEnum(Class<?> enumClass) {
        // 根据全路径名，判断所传类是否是枚举类
        return ENUM_CLASSPATH.equals(enumClass.getSuperclass().getCanonicalName());
    }

    /**
     * 通过反射获取枚举类的字段列表
     * @param enumClass
     * @return
     */
    private static List<Field> commonHandle(Class<?> enumClass) {

        // 获取所有public方法
        Method[] methods = enumClass.getMethods();

        List<Field> fieldList = new ArrayList<>();

        // 1.通过get方法提取字段【避免get作为自定义方法的开头,建议使用'find'或其余命名】
        // 使用stream流遍历方法数组，调用getName()方法获取方法名，使用startsWith()判断方法名是否已get开头，
        // 并排除getDeclaringClass()、getClass()两个特殊方法
        // 使用substring()截取掉前面的get三个字符，然后使用uncapitalize()将首字母转小写，得到字段名
        // 使用getDeclaredField()方法根据字段名获取字段
        // 获取到的字段不为空，则加入列表，获取失败则抛异常
        Arrays.stream(methods).map(Method::getName)
                .filter(methodName -> methodName.startsWith("get")
                        && !"getDeclaringClass".equals(methodName)
                        && !"getClass".equals(methodName))
                .forEachOrdered(methodName -> {
                    try {
                        Field field = enumClass.getDeclaredField(StringUtils.uncapitalize(methodName.substring(3)));
                        if (!ObjectUtils.isEmpty(field)) {
                            fieldList.add(field);
                        }
                    } catch (NoSuchFieldException | SecurityException e) {
                        e.printStackTrace();
                    }
                });

        return fieldList;
    }

    /**
     * 枚举类转换MapList
     * @param enumClass 枚举类
     * @return
     */
    public static List<Map<String, Object>> enumToMapList(Class<?> enumClass) {

        List<Map<String, Object>> resultList = new ArrayList<>();
        // 根据全路径名，判断所传类是否是枚举类
        if (!isEnum(enumClass)) {
            return resultList;
        }

        // 调用前面写的commonHandle()方法获取枚举类的字段列表
        List<Field> fieldList = commonHandle(enumClass);
        // 判断所获取到的字段列表是否为空
        if (CollectionUtils.isEmpty(fieldList)) {
            return resultList;
        }

        // 通过getEnumConstants()方法获取枚举常量，并强转为Enum[]类
        Enum<?>[] enums = (Enum[]) enumClass.getEnumConstants();
        Map<String, Object> map = null;

        // 遍历枚举常量数组，嵌套遍历字段数组，将枚举常量值存入map list中
        for (Enum<?> anEnum : enums) {

            map = new HashMap<>(fieldList.size());
            for (Field field : fieldList) {
                // setAccessible是否关闭访问安全检查，关闭检查可提高反射速度（据说性能有了20倍的提升）
                field.setAccessible(true);
                try {
                    // 向map集合添加字段名称和字段值
                    map.put(field.getName(), field.get(anEnum));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // 将Map添加到集合中
            resultList.add(map);
        }
        return resultList;
    }

    /**
     * 根据 code 的值获取其他字段的值
     * 适用于存在Integer code字段，且获取的属性值为string类型的枚举
     * @param enumClass 枚举类
     * @param code code的值
     * @param valueKey 需获取值的字段的名称
     * @return
     */
    public static String findEnumValueByCode(Class<?> enumClass, Integer code, String valueKey) {

        // 根据全路径名，判断所传类是否是枚举类
        if (!isEnum(enumClass)) {
            return null;
        }

        // 调用前面写的commonHandle()方法获取枚举类的字段列表
        List<Field> fieldList = commonHandle(enumClass);
        // 判断所获取到的字段列表是否为空
        if (CollectionUtils.isEmpty(fieldList)) {
            return null;
        }

        // 通过getEnumConstants()方法获取枚举常量，并强转为Enum[]类
        Enum<?>[] enums = (Enum[]) enumClass.getEnumConstants();
        Map<String, Object> map = null;
        boolean flag = false;

        // 遍历枚举常量数组，嵌套遍历字段数组，将枚举常量值存入map list中
        for (Enum<?> anEnum : enums) {
            System.out.println(anEnum);

            map = new HashMap<>(fieldList.size());
            for (Field field : fieldList) {
                // setAccessible是否关闭访问安全检查，关闭检查可提高反射速度（据说性能有了20倍的提升）
                field.setAccessible(true);
                try {
                    String key = field.getName();
                    Object value = field.get(anEnum);
                    // 向map集合添加字段名称和字段值
                    map.put(key, value);
                    //找到了
                    if ("code".equals(key) && code.equals((Integer) value))
                        flag = true;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // 返回找到的结果
            if (flag)
                return (String) map.get(valueKey);
        }

        return null;
    }


    /**
     * 通过code值查找对应枚举常量的名称
     * @param enumClass
     * @param code
     * @return
     * @throws IllegalAccessException
     */
    public static String findConstantNameByCode(Class<? extends Enum<?>> enumClass, Integer code) {
        return findConstantNameByField(enumClass, "code", code);
    }

    /**
     * 通过指定字段获取对应的枚举常量名称
     * @param enumClass 枚举类
     * @param key 字段名称
     * @param value 字段值
     * @return 枚举常量名称，未找到的话为null
     * @throws IllegalAccessException
     */
    public static String findConstantNameByField(Class<? extends Enum<?>> enumClass, String key, Object value) {

        // 根据全路径名，判断所传类是否是枚举类
        if (!isEnum(enumClass)) {
            return null;
        }

        // 调用前面写的commonHandle()方法获取枚举类的字段列表
        List<Field> fieldList = commonHandle(enumClass);
        // 判断所获取到的字段列表是否为空
        if (CollectionUtils.isEmpty(fieldList)) {
            return null;
        }

        // 通过getEnumConstants()方法获取枚举常量，并强转为Enum[]类
        Enum<?>[] enums = (Enum[]) enumClass.getEnumConstants();

        // 遍历枚举常量数组，嵌套遍历字段数组，将枚举常量值存入map list中
        for (Enum<?> anEnum : enums) {
            for (Field field : fieldList) {
                // setAccessible是否关闭访问安全检查。关闭检查可提高反射速度（据说性能有了20倍的提升）
                field.setAccessible(true);

                // 找到了，返回常量名
                try {
                    if (key.equals(field.getName()) && value.equals(field.get(anEnum)))
                        return anEnum.name();
                } catch (IllegalAccessException e) {
                    // log.error("非法访问异常，反射访问私有变量报错。【{}】【{}】", e.getMessage(),e.getClass());
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void main(String[] args) throws IllegalAccessException {

        System.out.println(findConstantNameByField(MsgStatusEnum.class, "code", 10));
        // System.out.println(EnumUtil.notContains(MsgStatusEnum.class, "技术调用5"));
        // System.out.println(MsgStatusEnum.valueOf("TECHNOLOGY"));

        // List<String> list = EnumUtil.getNames(MsgStatusEnum.class);
        // for (String s : list) {
        //     System.out.println(s);
        // }

        // List<Object> list = EnumUtil.getFieldValues(MsgStatusEnum.class, "name");
        // for (Object s : list) {
        //     System.out.println(s);
        // }

        // Map<String,Object> map = EnumUtil.getNameFieldMap(MsgStatusEnum.class, "name");
        // System.out.println(map);

        // Long time1 = System.currentTimeMillis();
        // for (int i = 0; i < 100000; i++) {
        //     LinkedHashMap<String, MsgStatusEnum> linkedHashMap = EnumUtil.getEnumMap(MsgStatusEnum.class);
        // }
        //
        // Long time2 = System.currentTimeMillis();
        //
        // for (int i = 0; i < 100000; i++) {
        //     List<Map<String, Object>> list =NewEnumUtil.enumToMapList(AuditStatusEnum.class);
        // }
        //
        // Long time3 = System.currentTimeMillis();
        // System.out.println(time2 - time1);
        // System.out.println(time3 - time2);

        // System.out.println(Arrays.toString(AuditStatusEnum.values()));;
        // System.out.println(AuditStatusEnum.valueOf("NO_NEED_AUDIT"));;
        // System.out.println(Arrays.toString(SendChannelEnum.values()));
        // System.out.println(SendChannelEnum.valueOf("MAIL"));;
    }

}
