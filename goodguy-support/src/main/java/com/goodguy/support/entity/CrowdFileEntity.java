package com.goodguy.support.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 人群文件表
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 09:17:36
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@TableName("support_crowd_file")
public class CrowdFileEntity {

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件路径
     */
    @TableField("`path`")
    private String path;

    /**
     * 文件大小
     */
    @TableField("size")
    private Integer size;

    /**
     * 原始文件名
     */
    @TableField("`name`")
    private String name;

    /**
     * 是否删除：0-不删除；1-删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
