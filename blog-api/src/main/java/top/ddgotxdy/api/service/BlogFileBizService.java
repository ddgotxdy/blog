package top.ddgotxdy.api.service;

import org.springframework.web.multipart.MultipartFile;
import top.ddgotxdy.api.model.addparam.ImageAddApiParam;
import top.ddgotxdy.api.model.queryparam.ImageQueryApiParam;
import top.ddgotxdy.api.model.updateparam.ImageUpdateApiParam;
import top.ddgotxdy.api.model.view.ImagePageListView;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.IdsView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;

import java.util.List;

/**
 * @author: ddgo
 * @description: 博客文件上传类
 */
public interface BlogFileBizService {
    /**
     * 上传图片接口，返回 url
     * @param imageFile 图片
     * @return url
     */
    String uploadImage(MultipartFile imageFile);

    /**
     * 更新图片接口，返回 id
     * @param imageUpdateApiParam 更新图片api请求
     * @return id 视图
     */
    IdView updateImage(ImageUpdateApiParam imageUpdateApiParam);

    /**
     * 分页查询图片接口
     * @param imageQueryApiParamPageQry 分页请求参数
     * @return 分页结果
     */
    PageResult<ImagePageListView> queryImageByPage(PageQry<ImageQueryApiParam> imageQueryApiParamPageQry);

    /**
     * 删除图片
     * @param imageList 图片列表
     * @return ids 视图
     */
    IdsView deleteImage(List<Long> imageList);

    /**
     * 恢复图片
     * @param imageList 图片列表
     * @return ids 视图
     */
    IdsView recoverImage(List<Long> imageList);

    /**
     * 添加图片
     * @param imageAddApiParam 添加图片参数
     * @return id 视图
     */
    IdView addImage(ImageAddApiParam imageAddApiParam);
}
