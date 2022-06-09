package com.goodguy.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goodguy.admin.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
