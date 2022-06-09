package com.goodguy.api.model;

import com.goodguy.api.pojo.MessageParamPojo;
import com.goodguy.api.pojo.TaskInfoPojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发送消息任务模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel {
    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 消息参数（消息接收者、消息内容等）
     */
    private List<MessageParamPojo> messageParamList;

    /**
     * 消息任务详情
     */
    private List<TaskInfoPojo> taskInfo;
}
