package com.ddgotxdy.service;

import com.ddgotxdy.vo.RegisterParam;
import com.ddgotxdy.vo.Result;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ddgo
 * @description: 注册服务，需要添加事务回滚
 */
@Transactional(rollbackFor = Exception.class)
public interface RegisterService {

    /**
     * 用户注册
     * @param registerParam 注册参数
     * @return Result
     */
    Result register(RegisterParam registerParam);
}
