package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginParam;
import org.apache.commons.lang3.tuple.Triple;

/**
 * 登录前置过滤器
 * 可以用来做参数校验,账户黑名单,登录方式渠道临时关闭等前置操作
 */
public interface LoginBeforeFilter {

    Triple<Integer, String, LoginParam> filter(LoginParam loginParam);

    int getOrder();
}
