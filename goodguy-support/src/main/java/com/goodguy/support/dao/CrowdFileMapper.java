package com.goodguy.support.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goodguy.support.entity.CrowdFileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 人群文件信息 Mapper 接口
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Mapper
public interface CrowdFileMapper extends BaseMapper<CrowdFileEntity> {
}
