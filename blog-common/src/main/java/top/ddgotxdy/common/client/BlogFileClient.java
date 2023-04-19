package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.model.ResultView;

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
    @PostMapping("openfeign/file/upload")
    ResultView<String> upload(
            @RequestParam("image") MultipartFile imageFile
    );
}
