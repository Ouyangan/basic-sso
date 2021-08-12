package an.ouyang.basic.sso.support.mobilecode;


import an.ouyang.basic.sso.LoginPreParam;
import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginType;
import an.ouyang.basic.sso.LoginParam;
import an.ouyang.basic.sso.filter.BlackListBeforeFilter;
import an.ouyang.basic.sso.filter.RenewalTokenAfterFilter;
import an.ouyang.basic.sso.service.AbstractLoginService;
import an.ouyang.basic.sso.service.LoginStore;
import an.ouyang.basic.sso.service.RedisLoginStore;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MobileCodeLoginService extends AbstractLoginService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void close() {
        super.close();
        //释放其他资源,比如rpc客户端等
    }

    @Override
    public void init() {
        super.init();
        addBeforeFilter(new BlackListBeforeFilter(stringRedisTemplate));
        addAfterFilter(new RenewalTokenAfterFilter(getLoginStore()));
    }

    @Override
    protected Triple<Integer, String, LoginToken> doLogin(LoginParam loginParam) {

        throw new RuntimeException("todo");
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.mobileCode;
    }

    @Override
    public Triple<Integer, String, Object> preLogin(LoginPreParam loginPreParam) {
        return null;
    }

    @Override
    public LoginStore getLoginStore() {
        return new RedisLoginStore(stringRedisTemplate);
    }
}
