package an.ouyang.basic.sso;

import java.util.Map;

/**
 * 登录成功token对象
 */
public interface LoginToken {
    Long getUserId();

    String getUsername();

    String getHeadImg();

    Long getMerchantId();

    String getToken();

    Integer getTokenExpireTime();

    LoginType getLoginType();

    Long getCreateTime();

    Map<String, Object> getExtProperty();

}
