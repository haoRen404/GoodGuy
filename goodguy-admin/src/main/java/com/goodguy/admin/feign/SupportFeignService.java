package com.goodguy.admin.feign;

import com.goodguy.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("goodguy-support") // 设置从 goodguy-support 服务进行远程调用
public interface SupportFeignService {
    @GetMapping("/support/crowdFile/file/{id}")
    R getCrowdFile(@RequestParam("id") Long id);
}
