package com.goodguy.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goodguy.admin.dao.UserMapper;
import com.goodguy.admin.entity.UserEntity;
import com.goodguy.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息发送渠道 服务实现类
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 13:38:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
