package top.ddgotxdy.common.model.sms.queryparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CaptchaQueryParam {
    @Length(min = 1, max = 30)
    private String mail;
}
