package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.LoginParam;
import an.ouyang.basic.sso.LoginPreParam;
import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.filter.LoginAfterFilter;
import an.ouyang.basic.sso.filter.LoginBeforeFilter;
import an.ouyang.basic.sso.filter.LoginLogAfterFilter;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractLoginService extends AbstractLoginFilterService implements LoginService, InitializingBean {
    private final static Logger log = LoggerFactory.getLogger(AbstractLoginService.class);


    @Override
    public Triple<Integer, String, LoginToken> login(LoginParam loginParam) {
        //前置过滤器
        for (LoginBeforeFilter loginBeforeFilter : loginBeforeFilters) {
            Triple<Integer, String, LoginParam> preFilterResult = loginBeforeFilter.filter(loginParam);
            if (preFilterResult.getLeft() != 0) {
                return Triple.of(preFilterResult.getLeft(), preFilterResult.getMiddle(), null);
            }
        }
        //登录
        Triple<Integer, String, LoginToken> loginResult = doLogin(loginParam);
        //持久化token
        if (loginResult.getLeft().equals(0)) {
            getLoginStore().put(
                    loginResult.getRight().getToken(),
                    loginResult.getRight(),
                    getLoginType().getExpire(),
                    getLoginType().getTimeUnit());
        }
        //后置过滤器
        for (LoginAfterFilter loginAfterFilter : loginAfterFilters) {
            loginAfterFilter.filter(loginParam, loginResult);
        }
        return loginResult;
    }

    protected abstract Triple<Integer, String, LoginToken> doLogin(LoginParam loginParam);

    @Override
    public boolean logout(String token) {
        LoginStore loginStore = getLoginStore();
        boolean delete = loginStore.delete(token);
        if (delete) {
            log.info("token={} logout success", token);
            return true;
        }
        log.info("token={} logout fail", token);
        return false;
    }

    @Override
    public boolean verify(String token) {
        LoginToken loginToken = getLoginStore().get(token);
        if (Objects.isNull(loginToken)) {
            log.warn("token={} is expired", token);
            return false;
        }
        Integer tokenExpireTime = loginToken.getTokenExpireTime();
        if (tokenExpireTime < System.currentTimeMillis()) {
            log.warn("token={} is expired", token);
            return false;
        }
        log.info("token={} verify success", token);
        return true;
    }

    @Override
    public String getTokenKey() {
        return getLoginType().key(UUID.randomUUID().toString());
    }

    @Override
    public void init() {
        addAfterFilter(new LoginLogAfterFilter());
        LoginServiceFactory.map.put(getLoginType().name(), this);
    }

    @Override
    public void close() {
        loginBeforeFilters.clear();
        loginAfterFilters.clear();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
