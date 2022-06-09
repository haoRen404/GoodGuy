package com.goodguy.support.service;

import com.goodguy.common.vo.R;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    /**
     * 上传csv文件
     * @param file
     * @return
     */
    R uploadCSV(Long templateId, MultipartFile file);
}
