package com.goodguy.api.service;

import com.goodguy.api.vo.SendRequest;
import com.goodguy.api.vo.SendResponse;

public interface SendService {
    /**
     * 单模板单文案发送接口
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);

    /**
     * 单模板多文案发送接口
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(SendRequest batchSendRequest);
}
