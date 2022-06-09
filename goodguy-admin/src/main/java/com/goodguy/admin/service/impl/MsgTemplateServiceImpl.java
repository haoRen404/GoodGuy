package com.goodguy.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goodguy.admin.dao.MsgTemplateMapper;
import com.goodguy.admin.entity.MsgTemplateEntity;
import com.goodguy.admin.feign.SupportFeignService;
import com.goodguy.admin.po.TemplateAdminIndexPo;
import com.goodguy.admin.service.MsgTemplateService;
import com.goodguy.common.enums.ResponseStatusEnum;
import com.goodguy.common.enums.model.*;
import com.goodguy.common.utils.CommonUtil;
import com.goodguy.common.vo.IPageVo;
import com.goodguy.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import static com.goodguy.common.enums.model.MsgTypeEnum.findEnumConst;

/**
 * <p>
 * 消息模板信息 服务实现类
 * </p>
 *
 * @author 好人
 * @since 2022-05-21 08:22:03
 */
@Service
public class MsgTemplateServiceImpl extends ServiceImpl<MsgTemplateMapper, MsgTemplateEntity> implements MsgTemplateService {

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    MsgTemplateMapper msgTemplateMapper;

    @Autowired
    SupportFeignService supportFeignService;


    @Value("${my.sql.paging.templateIndex}")
    private Integer templateIndexPageCount; // 模板管理首页分页每页数量

    /**
     * 获取首页数据
     * @param currentPage 页码
     * @return
     */
    @Override
    public R getIndexData(Long currentPage) throws ExecutionException, InterruptedException {

        // 封装返回数据
        Map result = new HashMap<String, Object>();

        // 各种状态数据
        CompletableFuture stateDataFuture = CompletableFuture.runAsync(() -> {
            // 获取消息类型
            List<Map<String, Object>> msgTypeList = MsgTypeEnum.enumToMapList();
            result.put("msgTypeList", msgTypeList);

            // 获取调用类型
            List<Map<String, Object>> callTypeList = CallTypeEnum.enumToMapList();
            result.put("callTypeList", callTypeList);

            // 获取发送渠道类型
            List<Map<String, Object>> sendChannelList = SendChannelEnum.enumToMapList();
            result.put("sendChannelList", sendChannelList);

            // 获取人群文件id类型
            List<Map<String, Object>> crowdIdTypeList = CrowdIdTypeEnum.enumToMapList();
            result.put("crowdIdTypeList", crowdIdTypeList);

            // 获取审核状态类型
            List<Map<String, Object>> auditStatusList = AuditStatusEnum.enumToMapList();
            result.put("auditStatusList", auditStatusList);
        }, threadPoolExecutor);

        IPage<TemplateAdminIndexPo> iPage  = new Page<>();
        iPage.setCurrent(currentPage); // 第一页
        iPage.setSize(templateIndexPageCount);// 每页的记录数

        // 构建分页数据
        IPageVo iPageVo = IPageVo.builder()
                .current(currentPage)
                .size(iPage.getSize())
                .build();

        // 获取模板分页的模板list
        CompletableFuture templateAdminIndexPoListFuture = CompletableFuture.runAsync(() -> {
            List<TemplateAdminIndexPo> templateAdminIndexPoList = msgTemplateMapper.findTemplatePage(iPage);
            iPageVo.setRecords(templateAdminIndexPoList);
        }, threadPoolExecutor);

        // 获取模板总条数
        CompletableFuture templateAdminCountFuture = CompletableFuture.runAsync(() -> {
            // 获取总记录数
            Long templateAdminCount = msgTemplateMapper.selectCount(new QueryWrapper<>());
            iPageVo.setTotal(templateAdminCount);
            iPageVo.setPages((long) Math.ceil((double) templateAdminCount / templateIndexPageCount)); // 总页数，向上取整
        }, threadPoolExecutor);

        // allOf() 所有任务均需完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                stateDataFuture,
                templateAdminIndexPoListFuture,
                templateAdminCountFuture
        );
        allOf.get(); // 阻塞，等待全部任务完成

        result.put("iPageVo", iPageVo);

        R r = R.ok().setData(result);

        return r;
    }

    /**
     * 创建一个模板
     * @param template
     * @return
     */
    @Override
    public R addTemplate(MsgTemplateEntity template) {
        // TODO 流水号暂时用 NanoId 填充
        template.setFlowCode(CommonUtil.getNanoId());
        // TODO 记得添加用户
        template.setCreatorId(1L);

        if ("1".equals(template.getCron())) {
            template.setAuditStatus(40);
            template.setMsgStatus(10);
        } else {
            template.setAuditStatus(10);
            template.setMsgStatus(20);
        }

        Integer count = msgTemplateMapper.insert(template);
        if (count != 1) {
            return R.fail(ResponseStatusEnum.DATABASE_OPERATION_FAIL);
        }
        return R.ok();
    }

    /**
     * 获取模板数据
     * @param id 模板id
     * @return
     */
    @Override
    public MsgTemplateEntity getTemplate(Long id) {
        Wrapper wrapper = new QueryWrapper<MsgTemplateEntity>()
                .select("id", "name", "msg_type_code", "cron", "call_type_code", "crowd_id_type_code",
                        "msg_content", "send_channel_code", "send_account_code", "department", "`desc`")
                .eq("id", id);
        MsgTemplateEntity template = msgTemplateMapper.selectOne(wrapper);
        return template;
    }

    /**
     * 编辑模板
     * @param template 模板
     * @return
     */
    @Override
    public boolean editTemplate(MsgTemplateEntity template) {
        MsgTemplateEntity newTemplate = MsgTemplateEntity.builder()
                .id(template.getId())
                .name(template.getName())
                .msgTypeCode(template.getMsgTypeCode())
                .cron(template.getCron())
                .callTypeCode(template.getCallTypeCode())
                .crowdIdTypeCode(template.getCrowdIdTypeCode())
                .msgContent(template.getMsgContent())
                .sendChannelCode(template.getSendChannelCode())
                .sendAccountCode(template.getSendAccountCode())
                .department(template.getDepartment())
                .desc(template.getDesc())
                .build();
        int count = msgTemplateMapper.updateById(newTemplate);
        return count == 1;
    }

    /**
     * 获取查看模板的数据
     * @param id
     * @return
     */
    @Override
    public HashMap getSeeTemplate(Long id) throws ExecutionException, InterruptedException {

        HashMap templateMap = msgTemplateMapper.findSeeTemplate(id);
        if (templateMap == null)
            return null;

        CompletableFuture statusDataFuture = CompletableFuture.runAsync(() -> {
            // 消息类型
            Integer msgTypeCode = (Integer) templateMap.get("msgTypeCode");
            String msgTypeName = findEnumConst(msgTypeCode).getName();
            templateMap.put("msgTypeName", msgTypeName);

            // 调用类型
            Integer callTypeCode = (Integer) templateMap.get("callTypeCode");
            String callTypeName = CallTypeEnum.findEnumConst(callTypeCode).getName();
            templateMap.put("callTypeName", callTypeName);

            // 发送渠道
            Integer sendChannelCode = (Integer) templateMap.get("sendChannelCode");
            String sendChannelName = SendChannelEnum.findEnumConst(sendChannelCode).getName();
            templateMap.put("sendChannelName", sendChannelName);

            // 人群文件id类型
            Integer crowdIdTypeCode = (Integer) templateMap.get("crowdIdTypeCode");
            String crowdIdTypeName = SendChannelEnum.findEnumConst(crowdIdTypeCode).getName();
            templateMap.put("crowdIdTypeName", crowdIdTypeName);

            // 审核状态
            Integer auditStatusCode = (Integer) templateMap.get("auditStatus");
            String auditStatusName = SendChannelEnum.findEnumConst(auditStatusCode).getName();
            templateMap.put("auditStatusName", auditStatusName);
        }, threadPoolExecutor);

        CompletableFuture FileFuture = CompletableFuture.runAsync(() -> {
            Long fileId = (Long) templateMap.get("fileId");
            if (fileId != null && fileId > 0) {
                R crowdFileR = supportFeignService.getCrowdFile((Long) templateMap.get("fileId"));
                HashMap fileData = crowdFileR.getData(); // 反序列化
                templateMap.put("file", fileData);
            }
        }, threadPoolExecutor);

        // allOf() 所有任务均需完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                statusDataFuture,
                FileFuture
        );
        allOf.get(); // 阻塞，等待全部任务完成

        return templateMap;
    }

    /**
     * 删除一个模板
     * @param id
     * @return
     */
    @Override
    public boolean delTemplate(Long id) {
        if (id == null || id < 1)
            return false;

        Integer count = msgTemplateMapper.deleteById(id);
        if (count != 1)
            return false;

        return true;
    }

    /**
     * 设置模板的文件id
     * @param id 模板id
     * @param fileId 文件id
     * @return
     */
    @Override
    public boolean setFile(Long id, Long fileId) {
        if (id == null || id < 1 || fileId == null || fileId < 1)
            return false;

        MsgTemplateEntity msgTemplateEntity = MsgTemplateEntity.builder()
                .id(id)
                .fileId(fileId)
                .build();
        Integer count = msgTemplateMapper.updateById(msgTemplateEntity);

        System.out.println("count=" + count);

        return count == 1;
    }

    /**
     * 分页查询
     * @param currentPage 需要查询的页码
     * @param wrapper 查询条件
     * @return
     */
    public IPage<MsgTemplateEntity> pagingQuery(Integer currentPage, Wrapper<MsgTemplateEntity> wrapper){
        IPage<MsgTemplateEntity> iPage  = new Page<>();
        iPage.setCurrent(currentPage);//第一页
        iPage.setSize(templateIndexPageCount);// 每页的记录数

        // 分页查询
        IPage<MsgTemplateEntity> result = msgTemplateMapper.selectPage(iPage, wrapper);

        return result;
    }

}
