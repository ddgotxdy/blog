package top.ddgotxdy.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.ResultView;
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

    @PostMapping("/image/upload")
    public ResultView<String> uploadImage(
            @RequestParam("image") MultipartFile imageFile
    ) {
        String url = uploadService.uploadImage(imageFile);
        return ResultView.success(url);
    }

}
