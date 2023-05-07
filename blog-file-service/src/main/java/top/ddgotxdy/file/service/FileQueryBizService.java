package top.ddgotxdy.file.service;

import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.file.dto.ImagePageListDTO;
import top.ddgotxdy.common.model.file.queryparam.ImageQueryParam;

/**
 * @author: ddgo
 * @description: 文件查询接口
 */
public interface FileQueryBizService {
    /**
     * 分页查询接口
     * @param imageQueryParamPageQry 分页查询参数
     * @return PageResult<ImagePageListDTO>
     */
    PageResult<ImagePageListDTO> queryImageByPage(PageQry<ImageQueryParam> imageQueryParamPageQry);
}
