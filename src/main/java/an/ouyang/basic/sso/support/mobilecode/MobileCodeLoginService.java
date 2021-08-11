package an.ouyang.basic.sso.support.mobilecode;


import an.ouyang.basic.sso.LoginPreParam;
import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginType;
import an.ouyang.basic.sso.LoginVerifyParam;
import an.ouyang.basic.sso.filter.BlackListPreFilter;
import an.ouyang.basic.sso.filter.LoginLogAfterFilter;
import an.ouyang.basic.sso.filter.RenewalTokenAfterFilter;
import an.ouyang.basic.sso.service.*;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MobileCodeLoginService extends AbstractLoginService implements InitializingBean, ServiceInit {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void init() {
        addPreFilter(new BlackListPreFilter(stringRedisTemplate));
        addAfterFilter(new LoginLogAfterFilter());
        addAfterFilter(new RenewalTokenAfterFilter(getLoginStore()));
        LoginServiceFactory.map.put(getLoginType().name(), this);
    }

    @Override
    public void afterPropertiesSet() {
        init();
    }

    @Override
    protected Triple<Integer, String, LoginToken> doLogin(LoginVerifyParam loginVerifyParam) {
        throw new RuntimeException("todo");
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.mobileCode;
    }

    @Override
    public Triple<Integer, String, Object> preLogin(LoginPreParam loginPreParam) {
        throw new RuntimeException("todo");
    }

    @Override
    public LoginStore getLoginStore() {
        return new RedisLoginStore(stringRedisTemplate);
    }
}
