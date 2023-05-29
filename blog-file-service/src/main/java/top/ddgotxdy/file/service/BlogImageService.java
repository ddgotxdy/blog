package top.ddgotxdy.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogImage;

import java.util.List;

/**
 * @author: ddgo
 * @description: 图片上传
 */
public interface BlogImageService extends IService<BlogImage> {

    /**
     * 根据 image id list 删除
     * @param imageIds 图片id列表
     * @return boolean
     */
    boolean deleteByImageIdList(List<Long> imageIds);

    /**
     * 根据 image id list 恢复
     * @param imageIds 图片id列表
     * @return boolean
     */
    boolean recoverByImageIdList(List<Long> imageIds);
}
