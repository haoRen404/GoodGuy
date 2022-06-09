package com.goodguy.common.bo;

import lombok.*;

/**
 * 枚举资源资源类
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnumResourceBO {
    /**
     * 代码
     */
    private Integer code;

    /**
     * 注释
     */
    private String msg;
}
