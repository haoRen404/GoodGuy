package com.goodguy.support.controller;

import com.goodguy.common.enums.ResponseStatusEnum;
import com.goodguy.common.vo.R;
import com.goodguy.support.entity.CrowdFileEntity;
import com.goodguy.support.service.CrowdFileService;
import com.goodguy.support.utils.UploadOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/support/crowdFile")
public class CrowdFileController {

    @Autowired
    CrowdFileService crowdFileService;
    @Autowired
    UploadOSSUtil uploadOSSUtil;

    @GetMapping("/file/{id}")
    // public String getCrowdFile(@PathVariable("id") Long id) {
    //     System.out.println("id = " + id);
    //     if (id == null || id < 1) {
    //         return ResponseStatusEnum.PARAMETER_CHECK_FAIL.getMsg();
    //     }
    //
    //     // 获取crowdFile数据
    //     CrowdFileEntity crowdFile = crowdFileService.getCrowdFile(id);
    //     System.out.println("crowdFile = " + crowdFile);
    //     if (crowdFile == null) {
    //         System.out.println("null ---- null");
    //     }
    //     // 获取文件下载链接
    //     String url = uploadOSSUtil.getDownloadUrl(crowdFile.getPath());
    //     crowdFile.setPath(url);
    //
    //     return url;
    // }
    public R getCrowdFile(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        if (id == null || id <= 1) {
            return R.fail(ResponseStatusEnum.PARAMETER_CHECK_FAIL);
        }

        // 获取crowdFile数据
        CrowdFileEntity crowdFile = crowdFileService.getCrowdFile(id);
        // 获取文件下载链接
        String url = uploadOSSUtil.getDownloadUrl(crowdFile.getPath());
        crowdFile.setPath(url);

        return R.ok().setData(crowdFile);
    }

}
