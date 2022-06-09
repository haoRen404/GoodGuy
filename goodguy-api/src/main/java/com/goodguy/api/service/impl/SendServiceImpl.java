package com.goodguy.api.service.impl;

import com.goodguy.api.model.ProcessContextModel;
import com.goodguy.api.model.SendTaskModel;
import com.goodguy.api.service.ProcessContextService;
import com.goodguy.api.service.SendService;
import com.goodguy.api.vo.BasicResultVO;
import com.goodguy.api.vo.SendRequest;
import com.goodguy.api.vo.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    ProcessContextService processContextService;

    /**
     * 单模板单文案发送接口实现
     * @param sendRequest
     * @return
     */
    @Override
    public SendResponse send(SendRequest sendRequest) {
        // 封装发送消息任务
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                // Collections.singletonList() 返回只有一个元素的集合
                .messageParamList(Collections.singletonList(sendRequest.getMessageParamPojo()))
                .build();

        // 封装责任链上下文
        ProcessContextModel context = ProcessContextModel.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        // 执行责任链
        ProcessContextModel process = processContextService.process(context);

        return new SendResponse(process.getResponse().getStatus() + "", process.getResponse().getMsg());
    }

    /**
     * 单模板多文案发送接口实现
     * @param batchSendRequest
     * @return
     */
    @Override
    public SendResponse batchSend(SendRequest batchSendRequest) {
        return null;
    }
}
