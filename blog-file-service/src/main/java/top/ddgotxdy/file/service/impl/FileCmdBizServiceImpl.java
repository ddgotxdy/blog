package top.ddgotxdy.file.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;
import top.ddgotxdy.file.adaptor.FileManageAdaptor;
import top.ddgotxdy.file.convert.Param2ContextConvert;
import top.ddgotxdy.file.model.FileContext;
import top.ddgotxdy.file.service.FileCmdBizService;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description:
 */
@Slf4j
@Service
public class FileCmdBizServiceImpl implements FileCmdBizService {
    @Resource
    private FileManageAdaptor fileManageAdaptor;
    @Override
    public IdDTO addImage(ImageAddParam imageAddParam) {
        FileContext fileContext = Param2ContextConvert.addParamConvert(imageAddParam);
        log.info("FileCmdBizServiceImpl addImage request[{}]", toJSON(fileContext));
        fileManageAdaptor.execute(fileContext);
        return IdDTO.builder()
                .id(fileContext.getImageId())
                .build();
    }

    @Override
    public IdDTO updateImage(ImageUpdateParam imageUpdateParam) {
        FileContext fileContext = Param2ContextConvert.updateParamConvert(imageUpdateParam);
        log.info("FileCmdBizServiceImpl updateImage request[{}]", toJSON(fileContext));
        fileManageAdaptor.execute(fileContext);
        return IdDTO.builder()
                .id(fileContext.getImageId())
                .build();
    }
}
