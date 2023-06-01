package top.ddgotxdy.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.api.model.addparam.AboutMeAddApiParam;
import top.ddgotxdy.api.model.addparam.PageConfigAddApiParam;
import top.ddgotxdy.api.model.queryparam.OplogQueryApiParam;
import top.ddgotxdy.api.model.view.OplogPageListView;
import top.ddgotxdy.api.model.view.PageConfigView;
import top.ddgotxdy.api.service.BlogWebsiteBizService;
import top.ddgotxdy.common.enums.OplogStage;
import top.ddgotxdy.common.enums.OplogType;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.service.BlogOplogService;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.common.util.RedisCache;
import top.ddgotxdy.dal.entity.BlogOplog;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.common.constant.RedisPrefix.ABOUT_ME;
import static top.ddgotxdy.common.constant.RedisPrefix.PAGE_CONFIG;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogWebsiteBizServiceImpl implements BlogWebsiteBizService {
    @Resource
    private RedisCache redisCache;
    @Resource
    private BlogOplogService blogOplogService;

    @Override
    public void addAboutMe(AboutMeAddApiParam aboutMeAddApiParam) {
        String aboutMe = aboutMeAddApiParam.getAboutMe();
        redisCache.setCacheObject(ABOUT_ME, aboutMe);
    }

    @Override
    public String queryAboutMe() {
        return redisCache.getCacheObject(ABOUT_ME);
    }

    @Override
    public void addPage(PageConfigAddApiParam pageConfigAddApiParam) {
        redisCache.setCacheObject(PAGE_CONFIG, pageConfigAddApiParam);
    }

    @Override
    public PageConfigView queryPage() {
        PageConfigAddApiParam pageConfigAddApiParam = redisCache.getCacheObject(PAGE_CONFIG);
        PageConfigView pageConfigView = new PageConfigView();
        BeanCopyUtil.copyProperties(pageConfigAddApiParam, pageConfigView);
        return pageConfigView;
    }

    @Override
    public PageResult<OplogPageListView> queryOplogByPage(PageQry<OplogQueryApiParam> oplogQueryApiParamPageQry) {
        // 分页参数组装
        int pageNum = oplogQueryApiParamPageQry.getPageNum();
        int pageSize = oplogQueryApiParamPageQry.getPageSize();
        Page<BlogOplog> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogOplog> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        OplogQueryApiParam queryParam = oplogQueryApiParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getOperatorId()), BlogOplog::getOperatorId, queryParam.getOperatorId())
                .eq(Objects.nonNull(queryParam.getUserId()), BlogOplog::getUserId, queryParam.getUserId())
                .like(Objects.nonNull(queryParam.getOperatorType()), BlogOplog::getOperatorType, queryParam.getOperatorType().getCode())
                .like(Objects.nonNull(queryParam.getOperatorStage()), BlogOplog::getOperatorStage, queryParam.getOperatorStage().getCode());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = oplogQueryApiParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        Boolean createTime = orderByFields.get("createTime");
        queryWrapper.orderBy(true, createTime, BlogOplog::getCreateTime);
        Page<BlogOplog> blogOplogPage = blogOplogService.page(page, queryWrapper);
        List<BlogOplog> blogOplogList = blogOplogPage.getRecords();
        List<OplogPageListView> oplogPageListViews
                = BeanCopyUtil.copyListProperties(blogOplogList, OplogPageListView::new);
        for (int i = 0; i < oplogPageListViews.size(); i++) {
            BlogOplog blogOplog = blogOplogList.get(i);
            OplogPageListView oplogPageListView = oplogPageListViews.get(i);
            oplogPageListView.setOperatorStage(OplogStage.of(blogOplog.getOperatorStage()));
            oplogPageListView.setOperatorType(OplogType.of(blogOplog.getOperatorType()));
        }
        // 封装返回值
        PageResult<OplogPageListView> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogOplogPage.getPages());
        pageResult.setData(oplogPageListViews);
        return pageResult;
    }
}
