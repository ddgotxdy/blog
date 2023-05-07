package top.ddgotxdy.file.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;
import top.ddgotxdy.file.service.FileCmdBizService;
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
}
