package com.goodguy.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 发送渠道表
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("admin_user")
public class UserEntity {

    /**
     * 唯一标识
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工工号
     */
    @TableField("job_id")
    private String jobId;

    /**
     * 真实姓名
     */
    @TableField("`name`")
    private String name;

    /**
     * 平台昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱地址
     */
    @TableField("email_addr")
    private String emailAddr;

    /**
     * 密码
     */
    @TableField("`password`")
    private String password;

    /**
     * 工作部门
     */
    @TableField("department")
    private String department;

    /**
     * 账号等级：0-冻结，10-root权限，20-高级权限，30-普通权限
     */
    @TableField("`level`")
    private Integer level;

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
