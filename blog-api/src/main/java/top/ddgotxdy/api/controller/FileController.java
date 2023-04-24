package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.api.model.addparam.ImageAddApiParam;
import top.ddgotxdy.api.model.queryparam.ImageQueryApiParam;
import top.ddgotxdy.api.model.updateparam.ImageUpdateApiParam;
import top.ddgotxdy.api.model.view.ImagePageListView;
import top.ddgotxdy.api.service.BlogFileBizService;
import top.ddgotxdy.common.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ddgo
 * @description: 文件接口
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件接口")
public class FileController {
    @Resource
    private BlogFileBizService blogFileBizService;

    /**
     * 上传图片接口，并且添加到数据库
     * @param imageFile MultipartFile
     * @return 返回 url
     */
    @ApiOperation("上传图片")
    @PostMapping("admin/image/upload")
    public ResultView<String> uploadImage(
            @RequestParam("image") MultipartFile imageFile
    ) {
        String url = blogFileBizService.uploadImage(imageFile);
        return ResultView.success(url);
    }

    @ApiOperation("添加图片")
    @PostMapping("/admin/image/add")
    public ResultView<IdView> addTag(
            @Validated @RequestBody ImageAddApiParam imageAddApiParam
    ) {
        IdView idView = blogFileBizService.addImage(imageAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("更新图片")
    @PostMapping("/admin/image/update")
    public ResultView<IdView> updateImage(
            @RequestBody ImageUpdateApiParam imageUpdateApiParam
    ) {
        IdView idView = blogFileBizService.updateImage(imageUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("图片分页查询")
    @PostMapping("admin/image/queryByPage")
    public ResultView<PageResult<ImagePageListView>> queryImageByPage(
            @Validated @RequestBody PageQry<ImageQueryApiParam> imageQueryApiParamPageQry
    ) {
        PageResult<ImagePageListView> result = blogFileBizService.queryImageByPage(imageQueryApiParamPageQry);
        return ResultView.success(result);
    }

    @ApiOperation("图片删除接口")
    @DeleteMapping("admin/image/delete")
    public ResultView<IdsView> deleteImage(
            @RequestBody List<Long> imageList
    ) {
        IdsView idView = blogFileBizService.deleteImage(imageList);
        return ResultView.success(idView);
    }

    @ApiOperation("标签恢复接口")
    @PostMapping("admin/image/recover")
    public ResultView<IdsView> recoverImage(
            @RequestBody List<Long> imageList
    ) {
        IdsView idView = blogFileBizService.recoverImage(imageList);
        return ResultView.success(idView);
    }
}
