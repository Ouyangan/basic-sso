package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.DefaultLoginToken;
import an.ouyang.basic.sso.LoginToken;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisLoginStore implements LoginStore {

    private StringRedisTemplate stringRedisTemplate;

    public RedisLoginStore(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean put(String key, LoginToken token, int expire, TimeUnit timeUnit) {
        String json = JSON.toJSONString(token);
        stringRedisTemplate.opsForValue().setIfAbsent(key, json, expire, timeUnit);
        return true;
    }

    @Override
    public LoginToken get(String key) {
        String json = stringRedisTemplate.opsForValue().get(key);
        return JSON.parseObject(json, DefaultLoginToken.class);
    }

    @Override
    public boolean delete(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }
}
