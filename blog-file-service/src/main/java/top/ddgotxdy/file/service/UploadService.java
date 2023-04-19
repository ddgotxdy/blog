package top.ddgotxdy.file.service;

/**
 * @author: ddgo
 * @description: 上传服务
 */
public interface UploadService {
    /**
     * 输入字节码返回对应的url
     * @param uploadBytes 字节码
     * @param fileName 文件名称
     * @return url
     */
    String uploadImage(byte[] uploadBytes, String fileName);
}
