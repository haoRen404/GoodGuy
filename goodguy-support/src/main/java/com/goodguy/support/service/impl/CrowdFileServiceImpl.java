package com.goodguy.support.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goodguy.common.enums.ResponseStatusEnum;
import com.goodguy.common.vo.R;
import com.goodguy.support.dao.CrowdFileMapper;
import com.goodguy.support.entity.CrowdFileEntity;
import com.goodguy.support.service.CrowdFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人群文件信息 服务实现类
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Service
public class CrowdFileServiceImpl extends ServiceImpl<CrowdFileMapper, CrowdFileEntity> implements CrowdFileService {

    @Autowired
    CrowdFileMapper crowdFileMapper;

    @Override
    public CrowdFileEntity getCrowdFile(Long id) {
        if (id == null || id < 1) {
            return null;
        }

        Wrapper wrapper = new QueryWrapper<CrowdFileEntity>()
                .select("id", "path", "name")
                .eq("id", id);
        CrowdFileEntity crowdFile = crowdFileMapper.selectOne(wrapper);

        return crowdFile;
    }
}
