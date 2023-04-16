package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.view.TagPageListView;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class DTO2ViewConvert {

    public static PageResult<TagPageListView> tagPageListDTO2View(PageResult<TagPageListDTO> tagPageListDTOPageResult) {
        // 范型赋值
        List<TagPageListDTO> data = tagPageListDTOPageResult.getData();
        List<TagPageListView> tagPageListViews = BeanCopyUtil.copyListProperties(data, TagPageListView::new);
        // 分页结果赋值
        PageResult<TagPageListView> tagPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(tagPageListDTOPageResult, tagPageListViewPageResult);
        tagPageListViewPageResult.setData(tagPageListViews);
        return tagPageListViewPageResult;
    }

}