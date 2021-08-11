package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginParam;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 黑名单
 */
public class BlackListBeforeFilter implements LoginBeforeFilter {

    private StringRedisTemplate stringRedisTemplate;

    public BlackListBeforeFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Triple<Integer, String, LoginParam> filter(LoginParam loginParam) {
        //todo 可以在这里做黑名单或者冻结用户校验
        return Triple.of(0, "success", loginParam);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
