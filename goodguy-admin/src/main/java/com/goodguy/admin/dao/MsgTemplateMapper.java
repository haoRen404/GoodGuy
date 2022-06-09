package com.goodguy.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goodguy.admin.entity.MsgTemplateEntity;
import com.goodguy.admin.po.TemplateAdminIndexPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 消息模板信息 Mapper 接口
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Mapper
public interface MsgTemplateMapper extends BaseMapper<MsgTemplateEntity> {

    /**
     * 模板分页
     * @param iPage
     * @return
     */
    List<TemplateAdminIndexPo> findTemplatePage(IPage<TemplateAdminIndexPo> iPage);

    HashMap findSeeTemplate(@PathVariable("id") Long id);

}
