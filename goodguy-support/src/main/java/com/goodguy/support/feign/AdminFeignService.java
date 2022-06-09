package com.goodguy.support.feign;

import com.goodguy.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("goodguy-admin") // 设置从 goodguy-admin 服务进行远程调用
public interface AdminFeignService {
    @PutMapping("/admin/template/setFile/{id}/{fileId}")
    R setFile(@PathVariable("id") Long id, @PathVariable("fileId") Long fileId);
}
