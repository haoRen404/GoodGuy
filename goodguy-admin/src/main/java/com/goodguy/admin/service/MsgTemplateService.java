package com.goodguy.admin.service;

import com.goodguy.admin.entity.MsgTemplateEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goodguy.common.vo.R;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 消息模板信息 服务类
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
public interface MsgTemplateService extends IService<MsgTemplateEntity> {

    /**
     * 或者模板管理首页数据
     * @param currentPage
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    R getIndexData(Long currentPage) throws ExecutionException, InterruptedException;

    /**
     * 新增一个模板
     * @param template
     * @return
     */
    R addTemplate(MsgTemplateEntity template);

    /**
     * 获取模板数据
     * @param id 模板id
     * @return
     */
    MsgTemplateEntity getTemplate(Long id);

    /**
     * 编辑模板
     * @param template
     * @return
     */
    boolean editTemplate(MsgTemplateEntity template);

    /**
     * 获取查看模板的数据
     * @param id
     * @return
     */
    HashMap getSeeTemplate(Long id) throws ExecutionException, InterruptedException;

    /**
     * 删除一个模板
     * @param id
     * @return
     */
    boolean delTemplate(Long id);

    /**
     * 设置模板的文件id
     * @param id 模板id
     * @param fileId 文件id
     * @return
     */
    boolean setFile(Long id, Long fileId);
}
