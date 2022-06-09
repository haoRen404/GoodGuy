package com.goodguy.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.goodguy.common.JSR303Group.AddGroup;
import com.goodguy.common.JSR303Group.UpdateGroup;
import com.goodguy.common.valid.CronValid;
import com.goodguy.common.valid.JsonOrNullValid;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SeeTemplateVo {
    /**
     * 唯一标识
     */
    private Long id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 消息类型
     */
    private Integer msgTypeName;

    /**
     * 发送时间cron表达式：0-立即发送,cron表达式-定时发送或周期发送
     */
    private String cron;

    /**
     * 人群文件中id的类型
     */
    private Integer idTypeName;

    /**
     * 调用类型：10-运营类，20-技术调用类
     */
    private Integer callTypeName;

    /**
     * 人群文件名字
     */
    private Long fileName;

    /**
     * 部门单位
     */
    private String department;

    /**
     * 备注
     */
    private String desc;

    /**
     * 消息内容。占位符用${name}表示
     */
    private String msgContent;

    /**
     * 消息发送渠道
     */
    private Integer sendChannelCode;

    /**
     * 发送账号，0为默认。（如短信可选腾讯云、阿里云）
     */
    private Integer sendAccountCode;

    /**
     * 渠道属性json字符串
     */
    private String channelAttrJson;

    /**
     * 当前消息审核状态，运营类需审核：10-待审核，20-审核成功，30-被拒绝，40-无需审核
     */
    private Integer auditStatus;

    /**
     * 流水号
     */
    private String flowCode;

    /**
     * 当前消息发送状态：10-新建，20-启用，30-停用，40-等待发送，50-发送中，60-发送成功，70-发送失败
     */
    private Integer msgStatus;

    /**
     * 创建者
     */
    private Long creatorName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
