package top.ddgotxdy.file.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;

/**
 * @author: ddgo
 * @description: 文件cud接口
 */
public interface FileCmdBizService {
    /**
     * 添加图片接口
     * @param imageAddParam 图片添加参数
     * @return idDTO
     */
    IdDTO addImage(ImageAddParam imageAddParam);

    /**
     * 更新图片接口
     * @param imageUpdateParam 图片更新参数
     * @return idDTO
     */
    IdDTO updateImage(ImageUpdateParam imageUpdateParam);
}
