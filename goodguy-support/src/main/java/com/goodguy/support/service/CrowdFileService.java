package com.goodguy.support.service;

import com.goodguy.support.entity.CrowdFileEntity;

/**
 * <p>
 * 人群文件 服务类
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
public interface CrowdFileService {

    /**
     * 获取人群文件
     * @param id
     * @return
     */
    CrowdFileEntity getCrowdFile(Long id);
}
