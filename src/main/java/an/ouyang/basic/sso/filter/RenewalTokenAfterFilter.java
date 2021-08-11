package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginType;
import an.ouyang.basic.sso.LoginParam;
import an.ouyang.basic.sso.service.LoginStore;
import org.apache.commons.lang3.tuple.Triple;

public class RenewalTokenAfterFilter implements LoginAfterFilter {
    private final LoginStore loginStore;

    public RenewalTokenAfterFilter(LoginStore loginStore) {
        this.loginStore = loginStore;
    }

    @Override
    public void filter(LoginParam loginParam, Triple<Integer, String, LoginToken> result) {
        LoginToken loginToken = loginStore.get(result.getRight().getToken());
        LoginType loginType = loginToken.getLoginType();
        loginStore.put(loginToken.getToken(), loginToken, loginType.getExpire(), loginType.getTimeUnit());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
