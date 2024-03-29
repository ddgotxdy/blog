package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.deleteparam.ImageDeleteParam;
import top.ddgotxdy.common.model.file.dto.ImagePageListDTO;
import top.ddgotxdy.common.model.file.queryparam.ImageQueryParam;
import top.ddgotxdy.common.model.file.recoverparam.ImageRecoverParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;

/**
 * @author: ddgo
 * @description: 文件服务接口
 */
@Component
@FeignClient("file-service")
public interface BlogFileClient {
    /**
     * 上传图片接口
     * @param imageFile MultipartFile
     * @return 返回 url
     */
    @PostMapping(value = "openfeign/file/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultView<String> uploadImage(
            @RequestPart("image") MultipartFile imageFile
    );

    /**
     * 添加图片接口
     * @param imageAddParam 图片添加参数
     * @return ResultView<IdDTO>
     */
    @PostMapping("openfeign/file/image/add")
    ResultView<IdDTO> addImage(
            @RequestBody ImageAddParam imageAddParam
    );

    /**
     * 更新图片接口
     * @param imageUpdateParam 图片更新接口
     * @return ResultView<IdDTO>
     */
    @PostMapping("openfeign/file/image/update")
    ResultView<IdDTO> updateImage(
            @RequestBody ImageUpdateParam imageUpdateParam
    );

    /**
     * 分页查询接口
     * @param imageQueryParamPageQry 分页查询参数
     * @return ResultView<PageResult<ImagePageListDTO>>
     */
    @PostMapping("openfeign/file/image/queryByPage")
    ResultView<PageResult<ImagePageListDTO>> queryImageByPage(
            @Validated @RequestBody PageQry<ImageQueryParam> imageQueryParamPageQry
    );

    /**
     * 图片删除接口
     * @param imageDeleteParam 图片删除参数
     * @return IdsDTO
     */
    @DeleteMapping("openfeign/file/image/delete")
    ResultView<IdsDTO> deleteImage(
            @Validated @RequestBody ImageDeleteParam imageDeleteParam
    );

    /**
     * 图片恢复接口
     * @param imageRecoverParam 图片恢复参数
     * @return IdsDTO
     */
    @PostMapping("openfeign/file/image/recover")
    ResultView<IdsDTO> recoverImage(
            @Validated @RequestBody ImageRecoverParam imageRecoverParam
    );

}
