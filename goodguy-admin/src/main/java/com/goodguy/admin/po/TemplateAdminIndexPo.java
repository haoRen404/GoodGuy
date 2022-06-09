package com.goodguy.admin.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TemplateAdminIndexPo {
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
    private Integer msgTypeCode;

    /**
     * 发送时间cron表达式：0-立即发送,cron表达式-定时发送或周期发送
     */
    private String cron;

    /**
     * 人群文件中id的类型
     */
    private Integer idTypeCode;

    /**
     * 调用类型：10-运营类，20-技术调用类
     */
    private Integer callTypeCode;

    /**
     * 人群文件id
     */
    private Long fileId;

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
     * 当前消息审核状态，运营类需审核：10-待审核，20-审核成功，30-被拒绝，40-无需审核
     */
    private Integer auditStatus;

    /**
     * 当前消息发送状态：10-新建，20-启用，30-停用，40-等待发送，50-发送中，60-发送成功，70-发送失败
     */
    private Integer msgStatus;

    /**
     * 创建者姓名
     */
    private String creatorName;
}
