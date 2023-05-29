package top.ddgotxdy.file.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.deleteparam.ImageDeleteParam;
import top.ddgotxdy.common.model.file.dto.ImagePageListDTO;
import top.ddgotxdy.common.model.file.queryparam.ImageQueryParam;
import top.ddgotxdy.common.model.file.recoverparam.ImageRecoverParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;
import top.ddgotxdy.file.service.FileCmdBizService;
import top.ddgotxdy.file.service.FileQueryBizService;
import top.ddgotxdy.file.service.UploadService;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 文件管理控制器
 */
@RestController
@RequestMapping("openfeign/file")
public class FileController {
    @Resource
    private UploadService uploadService;
    @Resource
    private FileCmdBizService fileCmdBizService;
    @Resource
    private FileQueryBizService fileQueryBizService;

    @PostMapping("/image/upload")
    public ResultView<String> uploadImage(
            @RequestParam("image") MultipartFile imageFile
    ) {
        String url = uploadService.uploadImage(imageFile);
        return ResultView.success(url);
    }

    @PostMapping("/image/add")
    public ResultView<IdDTO> addImage(
            @RequestBody ImageAddParam imageAddParam
    ) {
        IdDTO idDTO = fileCmdBizService.addImage(imageAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/image/update")
    public ResultView<IdDTO> updateImage(
            @RequestBody ImageUpdateParam imageUpdateParam
    ) {
        IdDTO idDTO = fileCmdBizService.updateImage(imageUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/image/queryByPage")
    public ResultView<PageResult<ImagePageListDTO>> queryImageByPage(
            @Validated @RequestBody PageQry<ImageQueryParam> imageQueryParamPageQry
    ) {
        PageResult<ImagePageListDTO> result = fileQueryBizService.queryImageByPage(imageQueryParamPageQry);
        return ResultView.success(result);
    }

    @DeleteMapping("/image/delete")
    public ResultView<IdsDTO> deleteImage(
            @Validated @RequestBody ImageDeleteParam imageDeleteParam
    ) {
        IdsDTO idsDTO = fileCmdBizService.deleteImage(imageDeleteParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/image/recover")
    public ResultView<IdsDTO> recoverImage(
            @Validated @RequestBody ImageRecoverParam imageRecoverParam
    ) {
        IdsDTO idsDTO = fileCmdBizService.recoverImage(imageRecoverParam);
        return ResultView.success(idsDTO);
    }
}
