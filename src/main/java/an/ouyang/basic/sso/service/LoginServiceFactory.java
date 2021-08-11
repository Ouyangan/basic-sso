package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.LoginType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginServiceFactory {
    public static Map<String, LoginService> map = new HashMap<>();

    public LoginService get(LoginType loginType) {
        LoginService loginService = map.get(loginType.name());
        if (Objects.isNull(loginService)) {
            throw new RuntimeException("登录类型未实现" + loginType.name());
        }
        return map.get(loginType.name());
    }
}
