package top.ddgotxdy.file.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.util.UUIDUtil;
import top.ddgotxdy.file.service.UploadService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: ddgo
 * @description: 文件管理控制器
 */
@RestController
@RequestMapping("openfeign/file")
public class FileController {
    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResultView<String> upload(
            @RequestParam("image") MultipartFile imageFile
    ) {
        byte[] uploadBytes = null;
        try {
            uploadBytes = imageFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 这里先默认自己构建名称
        String fileName = UUIDUtil.getUUID() + "."
                + StringUtils.substringAfterLast(imageFile.getOriginalFilename(), ".");
        String url = uploadService.uploadImage(uploadBytes, fileName);
        return ResultView.success(url);
    }

}
