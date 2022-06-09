package com.goodguy.common.vo;

import lombok.*;

/**
 * 封装统一的分页响应的po
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class IPageVo {
    /**
     * 记录列表
     */
    Object records;

    /**
     * 总页数
     */
    Long pages;

    /**
     * 总记录数
     */
    Long total;

    /**
     * 当前页码
     */
    Long current;

    /**
     * 每页记录数
     */
    Long size;
}
