package an.ouyang.basic.sso;

import java.util.Map;

/**
 * 登录前置操作参数
 * 可以用作校验用户名,手机号,商户号
 * 可以用于验证码登录时发送验证码
 */
public interface LoginPreParam {
    /**
     * 登录类型
     *
     * @return
     */
    LoginType getLoginType();

    /**
     * 登录商户
     * 如果oauth2的话也可能是appId
     *
     * @return
     */
    String getMerchantId();

    /**
     * 登录凭证key,可以是用户名/手机号,oauth2时可以为空
     *
     * @return
     */
    String getLoginKey();

    /**
     * 备用拓展字段
     *
     * @return
     */
    Map<String, String> getParameter();
}
