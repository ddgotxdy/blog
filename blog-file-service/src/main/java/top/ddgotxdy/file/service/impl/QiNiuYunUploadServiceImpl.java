package top.ddgotxdy.file.service.impl;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.common.util.UUIDUtil;
import top.ddgotxdy.file.service.UploadService;

import java.io.IOException;

/**
 * @author: ddgo
 * @description:
 */
@Slf4j
@Service
@RefreshScope
public class QiNiuYunUploadServiceImpl implements UploadService {
    @Value("${qiniu.url}")
    public String url;
    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.accessSecretKey}")
    private String accessSecretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    @Override
    public String uploadImage(MultipartFile imageFile) {
        byte[] uploadBytes = null;
        try {
            uploadBytes = imageFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 这里先默认自己构建名称
        String fileName = UUIDUtil.getUUID() + "."
                + StringUtils.substringAfterLast(imageFile.getOriginalFilename(), ".");
        // 构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, accessSecretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return url + fileName;
        } catch (Exception e) {
            log.error("Qi Niu Yun upload failed [{}]", e.toString());
        }
        return "";
    }
}
