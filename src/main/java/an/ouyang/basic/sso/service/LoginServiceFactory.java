package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.LoginType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginServiceFactory implements DisposableBean {
    public static Map<String, LoginService> map = new HashMap<>();

    public LoginService get(LoginType loginType) {
        LoginService loginService = map.get(loginType.name());
        if (Objects.isNull(loginService)) {
            throw new RuntimeException("登录类型未实现" + loginType.name());
        }
        return loginService;
    }

    @Override
    public void destroy() throws Exception {
        for (LoginService service : map.values()) {
            service.close();
        }
        map.clear();
    }
}
