package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginVerifyParam;
import an.ouyang.basic.sso.filter.LoginAfterFilter;
import an.ouyang.basic.sso.filter.LoginPreFilter;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractLoginService extends AbstractLoginFilterService implements LoginService {
    private final static Logger log = LoggerFactory.getLogger(AbstractLoginService.class);

    private final List<LoginPreFilter> loginPreFilters = new ArrayList<>();

    private final List<LoginAfterFilter> loginAfterFilters = new ArrayList<>();

    @Override
    public Triple<Integer, String, LoginToken> login(LoginVerifyParam loginVerifyParam) {
        //前置过滤器
        for (LoginPreFilter loginPreFilter : loginPreFilters) {
            Triple<Integer, String, LoginVerifyParam> preFilterResult = loginPreFilter.filter(loginVerifyParam);
            if (preFilterResult.getLeft() != 0) {
                return Triple.of(preFilterResult.getLeft(), preFilterResult.getMiddle(), null);
            }
        }
        //登录
        Triple<Integer, String, LoginToken> loginResult = doLogin(loginVerifyParam);
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
            loginAfterFilter.filter(loginVerifyParam, loginResult);
        }
        return loginResult;
    }

    protected abstract Triple<Integer, String, LoginToken> doLogin(LoginVerifyParam loginVerifyParam);

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

}
