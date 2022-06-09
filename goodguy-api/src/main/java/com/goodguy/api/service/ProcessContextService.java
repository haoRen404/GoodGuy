package com.goodguy.api.service;

import com.goodguy.api.model.ProcessContextModel;

public interface ProcessContextService {

    /**
     * 执行责任链
     * @param context
     * @return
     */
    ProcessContextModel process(ProcessContextModel context);

}
