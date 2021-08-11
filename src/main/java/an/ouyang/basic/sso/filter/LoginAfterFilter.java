package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginParam;
import org.apache.commons.lang3.tuple.Triple;

/**
 * 登录后置过滤器
 * 可以用来做日志记录,错误次数记录,token自动续期等操作
 */
public interface LoginAfterFilter {

    void filter(LoginParam loginParam, Triple<Integer, String, LoginToken> result);

    int getOrder();
}
