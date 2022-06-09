package com.goodguy.admin.controller;

import com.goodguy.admin.entity.MsgTemplateEntity;
import com.goodguy.admin.service.MsgTemplateService;
import com.goodguy.common.JSR303Group.AddGroup;
import com.goodguy.common.JSR303Group.UpdateGroup;
import com.goodguy.common.enums.ResponseStatusEnum;
import com.goodguy.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 消息模板信息 前端控制器
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Slf4j
@RestController
@RequestMapping("/admin/template")
public class MsgTemplateController {

    @Autowired
    MsgTemplateService msgTemplateService;

    /**
     * 获取模板管理首页数据
     * @param currentPage
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/templateBatch/{currentPage}")
    public R index(@PathVariable("currentPage") Long currentPage) throws ExecutionException, InterruptedException {
        log.info("进入首页，查看第{}页", currentPage);

        if (currentPage == null || currentPage <= 0)
            return R.fail(ResponseStatusEnum.LACK_PARAMETER.getMsg());

        R r = msgTemplateService.getIndexData(currentPage);

        return r;
    }

    /**
     * 创建一个模板
     * @param template
     * @return
     */
    @PostMapping("/template")
    public R addTemplate(@Validated({AddGroup.class}) @RequestBody MsgTemplateEntity template) {
        log.info("创建模板：{}", template);

        R r = msgTemplateService.addTemplate(template);

        return r;
    }

    /**
     * 获取模板数据（编辑模板时回显模板数据）
     * @param id
     * @return
     */
    @GetMapping("/template/{id}")
    public R getTemplate(@PathVariable("id") Long id) {
        log.info("获取模板数据：{}", id);

        if (id == null || id <= 0) {
            return R.fail(ResponseStatusEnum.LACK_PARAMETER);
        }

        MsgTemplateEntity template = msgTemplateService.getTemplate(id);
        if (template == null) {
            return R.fail(ResponseStatusEnum.DATA_NOT_EXIST);
        }
        return R.ok().setData(template);
    }

    /**
     * 编辑模板
     * @param template
     * @return
     */
    @PutMapping("/template")
    public R editTemplate(@Validated({UpdateGroup.class}) @RequestBody MsgTemplateEntity template) {
        log.info("编辑模板数据：{}", template);

        boolean flag = msgTemplateService.editTemplate(template);
        if (!flag) {
            return R.fail(ResponseStatusEnum.EDIT_FAILED);
        }
        return R.ok();
    }

    /**
     * 查看模板
     * @param id
     * @return
     */
    @GetMapping("/seeTemplate/{id}")
    public R seeTemplate(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {
        log.info("查看模板：{}", id);

        if (id == null || id <= 0) {
            return R.fail(ResponseStatusEnum.LACK_PARAMETER);
        }

        HashMap template = msgTemplateService.getSeeTemplate(id);
        if (template == null) {
            return R.fail(ResponseStatusEnum.DATA_NOT_EXIST);
        }

        return R.ok().setData(template);
    }

    /**
     * 删除模板
     * @param id
     * @return
     */
    @DeleteMapping("/template/{id}")
    public R delTemplate(@PathVariable("id") Long id) {
        log.info("删除模板：{}", id);

        if (id == null || id <= 0) {
            return R.fail(ResponseStatusEnum.LACK_PARAMETER);
        }

        boolean flag = msgTemplateService.delTemplate(id);

        return flag ? R.ok() : R.fail();
    }

    /**
     * 设置模板的文件id
     * @param id 模板id
     * @param fileId 文件id
     * @return
     */
    @PutMapping("/setFile/{id}/{fileId}")
    public R setFile(@PathVariable("id") Long id, @PathVariable("fileId") Long fileId) {
        System.out.println("设置模板文件喽：" + id + "\t" + fileId);
        if (id == null || id < 1 || fileId == null || fileId < 1)
            return R.fail(ResponseStatusEnum.PARAMETER_CHECK_FAIL);

        boolean flag = msgTemplateService.setFile(id, fileId);
        if (!flag)
            return R.fail();

        return R.ok();
    }
}
