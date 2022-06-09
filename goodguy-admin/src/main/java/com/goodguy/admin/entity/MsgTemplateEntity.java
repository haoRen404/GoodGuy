package com.goodguy.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.goodguy.common.JSR303Group.AddGroup;
import com.goodguy.common.JSR303Group.UpdateGroup;
import com.goodguy.common.valid.CronValid;
import com.goodguy.common.valid.JsonOrNullValid;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息模板信息
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@TableName("admin_msg_template")
public class MsgTemplateEntity {

    /**
     * 唯一标识
     */
    @NotNull(message = "模板ID不能为空", groups = {UpdateGroup.class})
    @Min(value = 1, message = "ID需大于0的整数", groups = {UpdateGroup.class})
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("`name`")
    private String name;

    /**
     * 消息类型
     */
    @Min(value = 1, message = "消息类型code需是大于等于1的整数", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("msg_type_code")
    private Integer msgTypeCode;

    /**
     * 发送时间cron表达式：0-立即发送,cron表达式-定时发送或周期发送
     */
    @CronValid(message = "Cron表达式不正确", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("cron")
    private String cron;

    /**
     * 人群文件中id的类型
     */
    // @Min(value = 1, message = "人群文件id类型code需是大于等于1的整数", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("crowd_id_type_code")
    private Integer crowdIdTypeCode;

    /**
     * 调用类型：10-运营类，20-技术调用类
     */
    @Min(value = 1, message = "调用类型code需是大于等于1的整数", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("call_type_code")
    private Integer callTypeCode;

    /**
     * 人群文件id
     */
    // @Min(value = 1, message = "人群文件id需是大于等于1的整数", groups = {AddGroup.class})
    @TableField("file_id")
    private Long fileId;

    /**
     * 部门单位
     */
    @TableField("department")
    private String department;

    /**
     * 备注
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 消息内容。占位符用${name}表示
     */
    @NotBlank(message = "消息内容不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("msg_content")
    private String msgContent;

    /**
     * 消息发送渠道
     */
    @Min(value = 1, message = "消息发送渠道code需是大于等于1的整数", groups = {AddGroup.class})
    @TableField("send_channel_code")
    private Integer sendChannelCode;

    /**
     * 发送账号，0为默认。（如短信可选腾讯云、阿里云）
     */
    @Min(value = 0, message = "发送账号code需是大于等于0的整数", groups = {AddGroup.class})
    @TableField("send_account_code")
    private Integer sendAccountCode;

    /**
     * 渠道属性json字符串
     */
    @JsonOrNullValid(message = "应为json格式字符串或空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("channel_attr_json")
    private String channelAttrJson;

    /**
     * 当前消息审核状态，运营类需审核：10-待审核，20-审核成功，30-被拒绝，40-无需审核
     */
    @TableField("audit_status")
    private Integer auditStatus;

    /**
     * 流水号
     */
    @TableField("flow_code")
    private String flowCode;

    /**
     * 当前消息发送状态：10-新建，20-启用，30-停用，40-等待发送，50-发送中，60-发送成功，70-发送失败
     */
    @TableField("msg_status")
    private Integer msgStatus;

    /**
     * 定时任务Id
     */
    @TableField("cron_task_id")
    private Long cronTaskId;

    /**
     * 是否删除：0-不删除；1-删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建者
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 最后的更新者
     */
    @TableField("updator_id")
    private Long updatorId;

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
