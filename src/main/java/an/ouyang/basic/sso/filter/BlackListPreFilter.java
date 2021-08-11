package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginVerifyParam;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 黑名单
 */
public class BlackListPreFilter implements LoginPreFilter {

    private StringRedisTemplate stringRedisTemplate;

    public BlackListPreFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Triple<Integer, String, LoginVerifyParam> filter(LoginVerifyParam loginVerifyParam) {
        //todo 可以在这里做黑名单或者冻结用户校验
        return Triple.of(0, "success", loginVerifyParam);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
