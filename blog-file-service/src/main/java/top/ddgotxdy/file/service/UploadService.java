package top.ddgotxdy.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: ddgo
 * @description: 上传服务
 */
public interface UploadService {
    /**
     * 输入字节码返回对应的url
     * @param imageFile 图片
     * @return url
     */
    String uploadImage(MultipartFile imageFile);
}
