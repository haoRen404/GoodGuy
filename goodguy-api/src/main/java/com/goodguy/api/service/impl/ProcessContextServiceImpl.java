package com.goodguy.api.service.impl;

import com.goodguy.api.model.ProcessContextModel;
import com.goodguy.api.service.ProcessContextService;
import org.springframework.stereotype.Service;

@Service
public class ProcessContextServiceImpl implements ProcessContextService {
    /**
     * 执行责任链
     * @param context
     * @return
     */
    @Override
    public ProcessContextModel process(ProcessContextModel context) {
        return null;
    }
}
