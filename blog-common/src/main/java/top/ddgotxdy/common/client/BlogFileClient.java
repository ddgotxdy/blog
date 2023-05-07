package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
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

}
