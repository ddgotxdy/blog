package top.ddgotxdy.common.model.sms.addparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CaptchaSendParam {
    @Length(min = 1, max = 30)
    private String mail;
}
