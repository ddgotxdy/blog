package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.api.convert.FileApiParam2ClientParamConvert;
import top.ddgotxdy.api.model.addparam.ImageAddApiParam;
import top.ddgotxdy.api.model.queryparam.ImageQueryApiParam;
import top.ddgotxdy.api.model.updateparam.ImageUpdateApiParam;
import top.ddgotxdy.api.model.view.ImagePageListView;
import top.ddgotxdy.api.service.BlogFileBizService;
import top.ddgotxdy.common.client.BlogFileClient;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogFileBizServiceImpl implements BlogFileBizService {
    @Resource
    private BlogFileClient blogFileClient;

    @Override
    public String uploadImage(MultipartFile imageFile) {
        ResultView<String> response = blogFileClient.uploadImage(imageFile);
        return response.getData();
    }

    @Override
    public IdView updateImage(ImageUpdateApiParam imageUpdateApiParam) {
        ImageUpdateParam imageUpdateParam = FileApiParam2ClientParamConvert.updateApiParam2updateParam(imageUpdateApiParam);
        ResultView<IdDTO> response = blogFileClient.updateImage(imageUpdateParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public PageResult<ImagePageListView> queryImageByPage(PageQry<ImageQueryApiParam> imageQueryApiParamPageQry) {
        return null;
    }

    @Override
    public IdsView deleteImage(List<Long> imageList) {
        return null;
    }

    @Override
    public IdsView recoverImage(List<Long> imageList) {
        return null;
    }

    @Override
    public IdView addImage(ImageAddApiParam imageAddApiParam) {
        ImageAddParam imageAddParam = FileApiParam2ClientParamConvert.addApiParam2AddParam(imageAddApiParam);
        ResultView<IdDTO> response = blogFileClient.addImage(imageAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }
}
