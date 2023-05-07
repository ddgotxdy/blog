package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.view.ImagePageListView;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.file.dto.ImagePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class FileDTO2ViewConvert {

    public static PageResult<ImagePageListView> imagePageListDTO2View(PageResult<ImagePageListDTO> imagePageListDTOPageResult) {
        // 范型赋值
        List<ImagePageListDTO> data = imagePageListDTOPageResult.getData();
        List<ImagePageListView> imagePageListViews = BeanCopyUtil.copyListProperties(data, ImagePageListView::new);
        // 分页结果赋值
        PageResult<ImagePageListView> imagePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(imagePageListDTOPageResult, imagePageListViewPageResult);
        // 赋值
        imagePageListViewPageResult.setData(imagePageListViews);
        return imagePageListViewPageResult;
    }
}
