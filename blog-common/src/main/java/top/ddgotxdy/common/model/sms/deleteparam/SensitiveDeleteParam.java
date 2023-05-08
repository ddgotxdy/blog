package top.ddgotxdy.common.model.sms.deleteparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class SensitiveDeleteParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 需要删除的id列表
     */
    private List<Long> sensitiveIds;
}
