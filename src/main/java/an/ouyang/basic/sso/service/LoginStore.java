package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.LoginToken;

import java.util.concurrent.TimeUnit;

public interface LoginStore {

    boolean put(String key, LoginToken token, int expire, TimeUnit timeUnit);

    LoginToken get(String key);

    boolean delete(String key);

}
