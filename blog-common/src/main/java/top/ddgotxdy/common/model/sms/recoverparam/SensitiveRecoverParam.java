package top.ddgotxdy.common.model.sms.recoverparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class SensitiveRecoverParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 需要恢复的id列表
     */
    private List<Long> sensitiveIds;
}
