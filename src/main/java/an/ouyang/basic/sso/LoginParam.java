package an.ouyang.basic.sso;


/**
 * 登录参数
 */
public interface LoginParam extends LoginPreParam {

    /**
     * 登录凭证,可以是密码,验证码,oauth2.code(授权码)
     *
     * @return
     */
    String getLoginSecurityCredentials();

    @Override
    String toString();

    Long getCreateTime();
}
