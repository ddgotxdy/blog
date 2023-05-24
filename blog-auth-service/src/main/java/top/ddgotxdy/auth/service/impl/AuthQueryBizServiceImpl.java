package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.convert.Entity2DTOConvert;
import top.ddgotxdy.auth.convert.FieldName2FunctionConvert;
import top.ddgotxdy.auth.service.AuthQueryBizService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.queryparam.RoleQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class AuthQueryBizServiceImpl implements AuthQueryBizService {
    @Resource
    private BlogUserService blogUserService;

    @Override
    public UserInfoDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        BlogUser user = loginUser.getUser();
        UserInfoDTO userInfoDTO = Entity2DTOConvert.user2DTO(user);
        return userInfoDTO;
    }

    @Override
    public PageResult<UserInfoPageListDTO> getUserInfoList(PageQry<UserInfoQueryParam> userInfoQueryParamPageQry) {
        // 分页参数组装
        int pageNum = userInfoQueryParamPageQry.getPageNum();
        int pageSize = userInfoQueryParamPageQry.getPageSize();
        Page<BlogUser> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        UserInfoQueryParam queryParam = userInfoQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getUserId()), BlogUser::getUserId, queryParam.getUserId())
                .eq(Objects.nonNull(queryParam.getUsernameEq()), BlogUser::getUsername, queryParam.getUsernameEq())
                .eq(Objects.nonNull(queryParam.getEmailEq()), BlogUser::getEmail, queryParam.getEmailEq())
                .eq(Objects.nonNull(queryParam.getSex()), BlogUser::getSex, queryParam.getSex())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogUser::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getUsername()), BlogUser::getUsername, queryParam.getUsername())
                .like(Objects.nonNull(queryParam.getEmail()), BlogUser::getEmail, queryParam.getEmail());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = userInfoQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.userFiledName2Function(name))
        );
        Page<BlogUser> blogUserPage = blogUserService.page(page, queryWrapper);
        List<BlogUser> blogUserList = blogUserPage.getRecords();
        List<UserInfoPageListDTO> userInfoPageListDTOList = Entity2DTOConvert.userList2DTO(blogUserList);
        // 封装返回值
        PageResult<UserInfoPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogUserPage.getPages());
        pageResult.setData(userInfoPageListDTOList);
        return pageResult;
    }

    @Override
    public PageResult<RolePageListDTO> queryRoleByPage(PageQry<RoleQueryParam> roleQueryParamPageQry) {
        return null;
    }
}
