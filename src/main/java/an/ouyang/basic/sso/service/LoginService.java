package an.ouyang.basic.sso.service;


import an.ouyang.basic.sso.LoginPreParam;
import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginType;
import an.ouyang.basic.sso.LoginParam;
import org.apache.commons.lang3.tuple.Triple;

/**
 * 登录接口定义
 */
public interface LoginService {
    void init();

    void close();

    /**
     * 登录类型
     */
    LoginType getLoginType();


    /**
     * 登录前置操作
     */
    Triple<Integer, String, Object> preLogin(LoginPreParam loginPreParam);

    /**
     * 登录接口
     */
    Triple<Integer, String, LoginToken> login(LoginParam loginParam);

    /**
     * 登出
     */
    boolean logout(String token);

    /**
     * 校验登录态
     */
    boolean verify(String token);

    /**
     * token存储引擎
     */
    LoginStore getLoginStore();


    String getTokenKey();
}
