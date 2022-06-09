package com.goodguy.support.service.impl;

import com.aliyun.oss.model.PutObjectResult;
import com.goodguy.common.utils.CommonUtil;
import com.goodguy.common.vo.R;
import com.goodguy.support.dao.CrowdFileMapper;
import com.goodguy.support.entity.CrowdFileEntity;
import com.goodguy.support.feign.AdminFeignService;
import com.goodguy.support.service.OssService;
import com.goodguy.support.utils.UploadOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    CrowdFileMapper crowdFileMapper;
    @Autowired
    UploadOSSUtil uploadOSSUtil;
    @Autowired
    AdminFeignService adminFeignService;

    /**
     * 上传csv文件
     * @param file
     * @return
     */
    @Override
    public R uploadCSV(Long templateId, MultipartFile file) {
        if (file.isEmpty())
            return R.fail("文件为null");

        // 原始文件名
        String originalFileName = file.getOriginalFilename();
        // 获取文件格式
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("fileType = " + fileType);
        if (!"CSV".equalsIgnoreCase(fileType))
            return R.fail("不支持非CSV文件");
        // 获取用户上传文件时指定的前缀
        String dir = uploadOSSUtil.getDir();
        // 新文件名
        String newFileName = dir + "crowd_" + CommonUtil.getNanoId() + "." + fileType;

        // 上传文件
        PutObjectResult putObjectResult = null;
        try {
            putObjectResult = uploadOSSUtil.uploadFile(file.getInputStream(), newFileName, originalFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail("文件上传OSS失败");
        }

        if (putObjectResult == null)
            return R.fail("文件上传OSS失败");

        // 文件入库
        CrowdFileEntity crowdFile = CrowdFileEntity.builder()
                .name(originalFileName)
                .path(newFileName)
                .size((int) file.getSize())
                .build();

        Integer count = crowdFileMapper.insert(crowdFile);
        if (count != 1) {
            return R.fail("文件入库失败");
        }

        // 更新模板的文件id
        R r = adminFeignService.setFile(templateId, crowdFile.getId());
        System.out.println("r = " + r);
        if (r.getCode() != 200) {
            return R.fail("更新模板文件id失败");
        }

        return R.ok();
    }
}
