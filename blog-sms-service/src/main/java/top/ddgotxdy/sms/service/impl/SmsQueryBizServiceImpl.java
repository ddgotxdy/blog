package top.ddgotxdy.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.model.sms.queryparam.SensitiveQueryParam;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.sms.convert.Entity2DTOConvert;
import top.ddgotxdy.sms.convert.FieldName2FunctionConvert;
import top.ddgotxdy.sms.service.BlogSensitiveService;
import top.ddgotxdy.sms.service.SmsQueryBizService;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class SmsQueryBizServiceImpl implements SmsQueryBizService {
    @Resource
    private BlogSensitiveService blogSensitiveService;

    @Override
    public PageResult<SensitivePageListDTO> querySensitiveByPage(PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry) {
        // 分页参数组装
        int pageNum = sensitiveQueryParamPageQry.getPageNum();
        int pageSize = sensitiveQueryParamPageQry.getPageSize();
        Page<BlogSensitive> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogSensitive> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        SensitiveQueryParam queryParam = sensitiveQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getSensitiveId()), BlogSensitive::getSensitiveId, queryParam.getSensitiveId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogSensitive::getIsDelete, queryParam.getIsDelete())
                .eq(Objects.nonNull(queryParam.getSensitiveType()), BlogSensitive::getSensitiveType, queryParam.getSensitiveType())
                .like(Objects.nonNull(queryParam.getWord()), BlogSensitive::getWord, queryParam.getWord());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = sensitiveQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.SensitiveFiledName2Function(name))
        );
        Page<BlogSensitive> blogSensitivePage = blogSensitiveService.page(page, queryWrapper);
        List<BlogSensitive> blogSensitiveList = blogSensitivePage.getRecords();
        List<SensitivePageListDTO> sensitivePageListDTOList = Entity2DTOConvert.SensitiveList2DTO(blogSensitiveList);
        // 封装返回值
        PageResult<SensitivePageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogSensitivePage.getPages());
        pageResult.setData(sensitivePageListDTOList);
        return pageResult;
    }
}
