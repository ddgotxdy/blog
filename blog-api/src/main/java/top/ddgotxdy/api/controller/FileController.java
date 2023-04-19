package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.client.BlogFileClient;
import top.ddgotxdy.common.model.ResultView;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 文件接口
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件接口")
public class FileController {
    @Resource
    private BlogFileClient blogFileClient;

    /**
     * 上传图片接口 TODO 先直接透传，后续优化一下
     * @param imageFile MultipartFile
     * @return 返回 url
     */
    @PostMapping("admin/upload")
    public ResultView<String> upload(
            @RequestParam("image") MultipartFile imageFile
    ) {
        return blogFileClient.upload(imageFile);
    }
}
